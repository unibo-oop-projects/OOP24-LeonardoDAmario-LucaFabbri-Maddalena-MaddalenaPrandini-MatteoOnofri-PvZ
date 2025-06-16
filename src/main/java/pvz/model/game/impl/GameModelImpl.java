package pvz.model.game.impl;

import pvz.model.bullets.api.Bullet;
import pvz.model.collisions.impl.HitBoxFactory;
import pvz.model.entities.api.EntitiesManager;
import pvz.model.entities.api.Entity;
import pvz.model.entities.api.EntityType;
import pvz.model.entities.api.GameEntity;
import pvz.model.entities.impl.EntitiesManagerImpl;
import pvz.model.game.api.Difficulty;
import pvz.model.game.api.GameModel;
import pvz.model.game.api.GameStatus;
import pvz.model.lawnmower.api.LawnMower;
import pvz.model.lawnmower.impl.LawnMowerImp;
import pvz.model.plants.api.Plant;
import pvz.model.plants.api.PlantType;
import pvz.model.plants.impl.PlantFactory;
import pvz.model.zombies.api.Zombie;
import pvz.model.zombies.impl.ZombieSpawnUtil;
import pvz.utilities.Position;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link GameModel} interface that manages the core game logic
 * including entity management, game state updates, and plant placement.
 */
public class GameModelImpl implements GameModel {
    private static final int ROWS = 5;
    private static final int COLS = 9;
    private static final int SPAWN_RATE = 5000;

    private static final Map<Difficulty, Integer> KILL_TO_WIN = Map.of(
            Difficulty.EASY, 20,
            Difficulty.NORMAL, 35,
            Difficulty.HARD, 45
    );

    private final EntitiesManager entitiesManager;
    private GameStatus status;
    private final Difficulty difficulty;
    private final List<Boolean> usedMower = new ArrayList<>(Collections.nCopies(ROWS, false));
    private long zombieLastSpawnTime;

    public GameModelImpl(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.entitiesManager = new EntitiesManagerImpl(difficulty);
        this.status = GameStatus.IN_PROGRESS;
    }

    @Override
    public boolean isGameOver() {
        return status != GameStatus.IN_PROGRESS;
    }

    @Override
    public boolean isVictory() {
        return status == GameStatus.WON;
    }

    @Override
    public void update(final long deltaTime) {
        if (status != GameStatus.IN_PROGRESS) {
            return;
        }

        spawnZombie(deltaTime);
        updateEntities(deltaTime);
        checkZombiesForMower();
        removeOutOfFrameObjects();
        checkGameStatus();
    }

    private void spawnZombie(final long deltaTime) {
        zombieLastSpawnTime += deltaTime;
        if (zombieLastSpawnTime >= SPAWN_RATE) {
            zombieLastSpawnTime = 0;
            Zombie zombie = ZombieSpawnUtil.generateRandomZombie(difficulty, ROWS);
            entitiesManager.addEntity(zombie);
        }
    }

    private void updateEntities(final long deltaTime) {
        entitiesManager.getEntities().forEach(e -> e.update(deltaTime, entitiesManager));
    }

    private void checkZombiesForMower() {
        entitiesManager.getEntities().stream()
                .filter(Zombie.class::isInstance)
                .map(Zombie.class::cast)
                .forEach(this::handleZombieAtRowStart);
    }

    private void handleZombieAtRowStart(Zombie zombie) {
        int row = (int) Math.round(zombie.getPosition().y());
        if (zombie.getPosition().x() <= 0) {
            if (usedMower.get(row)) {
                status = GameStatus.LOST;
            } else {
                addLawnMower(row);
            }
        }
    }

    private void addLawnMower(int row) {
        usedMower.set(row, true);
        LawnMower lawnMower = new LawnMowerImp(new Position(0, row), HitBoxFactory.HitBoxType.ZOMBIE);
        entitiesManager.addEntity(lawnMower);
        lawnMower.update(0, entitiesManager);
    }

    private void removeOutOfFrameObjects() {
        entitiesManager.getEntities().stream()
                .filter(e -> (e instanceof Bullet || e instanceof LawnMower) && e.getPosition().x() > COLS + 2)
                .collect(Collectors.toList())
                .forEach(entitiesManager::removeEntity);
    }

    private void checkGameStatus() {
        if (status == GameStatus.IN_PROGRESS && getKillCount() >= KILL_TO_WIN.get(difficulty)) {
            status = GameStatus.WON;
        }
    }

    @Override
    public Set<GameEntity> getGameEntities() {
        return entitiesManager.getEntities().stream()
                .map(e -> new GameEntity(getEntityTypeFromEntity(e), e.getPosition()))
                .collect(Collectors.toSet());
    }

    @Override
    public int getSunCount() {
        return entitiesManager.getSunCount();
    }

    @Override
    public int getKillCount() {
        return entitiesManager.getKillCount();
    }

    @Override
    public void placePlant(final EntityType type, final Position position) {
        PlantFactory plantFactory = new PlantFactory();
        PlantType plantType = getPlantTypeFromEntityType(type);
        final Plant plant = switch (plantType) {
            case PEASHOOTER -> plantFactory.createPeashooter(position);
            case SUNFLOWER -> plantFactory.createSunflower(position);
            case WALLNUT -> plantFactory.createWallnut(position);
        };
        if (entitiesManager.decreaseSun(plantType.getPrice())) {
            entitiesManager.addEntity(plant);
        }
    }

    private static EntityType getEntityTypeFromEntity(final Entity entity) {
        return switch (entity) {
            case Plant plant -> switch (plant.mapToEntityType()) {
                case PEASHOOTER -> EntityType.PEASHOOTER;
                case SUNFLOWER -> EntityType.SUNFLOWER;
                case WALLNUT -> EntityType.WALLNUT;
            };
            case Zombie zombie -> switch (zombie.getType()) {
                case BASICZOMBIE -> EntityType.BASICZOMBIE;
                case FASTZOMBIE -> EntityType.FASTZOMBIE;
                case STRONGZOMBIE -> EntityType.STRONGZOMBIE;
                case BEASTZOMBIE -> EntityType.BEASTZOMBIE;
            };
            case Bullet ignored -> EntityType.BULLET;
            case LawnMower ignored -> EntityType.LAWNMOWER;
            default -> throw new IllegalArgumentException();
        };
    }

    private static PlantType getPlantTypeFromEntityType(final EntityType entityType) {
        return switch (entityType) {
            case PEASHOOTER -> PlantType.PEASHOOTER;
            case SUNFLOWER -> PlantType.SUNFLOWER;
            case WALLNUT -> PlantType.WALLNUT;
            default -> throw new IllegalArgumentException();
        };
    }
}
