package pvz.model.impl;
import pvz.model.api.Bullets.Bullet;
import pvz.model.api.Difficulty;
import pvz.model.api.GameModel;
import pvz.model.api.GameStatus;
import pvz.model.api.Zombie;
import pvz.model.api.entities.EntitiesManager;
import pvz.model.api.entities.Entity;
import pvz.model.api.plants.Plant;
import pvz.model.api.plants.PlantType;
import pvz.model.impl.entities.EntitiesManagerImpl;
import pvz.model.impl.plants.PlantFactory;
import pvz.utilities.EntityType;
import pvz.utilities.GameEntity;
import pvz.utilities.Position;
import java.util.Set;
import java.util.stream.Collectors;
import pvz.view.impl.EndGameMenu.EndGameView;

import javax.swing.*;

/**
 * Implementation of the {@link GameModel} interface that manages the core game logic
 * including entity management, game state updates, and plant placement.
 */
public class GameModelImpl implements GameModel {
    private static final int TARGET_KILL_COUNT = 20;
    private final EntitiesManager entitiesManager;
    private final PlantFactory plantFactory;

    private GameStatus status;
    private final Difficulty difficulty;
    //private final ;

    /**
     * Constructs a new instance of the game model with default state and managers.
     */
    public GameModelImpl(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.entitiesManager = new EntitiesManagerImpl(difficulty);
        this.plantFactory = new PlantFactory();
        this.status = GameStatus.IN_PROGRESS;
    }


    @Override
    public boolean isGameOver() {
            return status != GameStatus.IN_PROGRESS;
    }

    /**
     * Checks whether the player has won the game. (Currently not implemented.)
     *
     * @return {@code true} if the player has achieved victory.
     * @throws UnsupportedOperationException always.
     */
    @Override
    public boolean isVictory() {
        return status == GameStatus.WON;
    }

    /**
     * Returns the current game status.
     *
     * @return the {@link GameStatus} value representing the game state.
     */
    @Override
    public GameStatus getGameStatus() {
        return status;
    }

    /**
     * Updates the state of the game based on the elapsed time.
     * This includes spawning zombies and updating all entities.
     *
     * @param deltaTime the time elapsed since the last update (in milliseconds).
     */
    @Override
    public void update(final long deltaTime) {
    this.entitiesManager.spawnZombie(deltaTime, difficulty);
    this.entitiesManager.getEntities().forEach(e -> {
        e.update(deltaTime, entitiesManager);
        if (e instanceof Zombie && e.getPosition().x() <= 0) {
            this.status = GameStatus.LOST;
        }
    });
    if(this.getKillCount()>=TARGET_KILL_COUNT) {
        this.status = GameStatus.WON;
    }
    }


    /**
     * Retrieves a set of all game entities currently present on the field,
     * mapped to their respective {@link GameEntity} representations.
     *
     * @return a set of all game entities.
     */
    @Override
    public Set<GameEntity> getGameEntities() {
        return entitiesManager.getEntities().stream()
                .map(e -> new GameEntity(getEntityType(e), e.getPosition()))
                .collect(Collectors.toSet());
    }

    /**
     * Gets the current amount of sun resources available to the player.
     *
     * @return the number of sun points.
     */
    @Override
    public int getSunCount() {
        return entitiesManager.getSunCount();
    }

    /**
     * Gets the number of zombies defeated by the player.
     *
     * @return the total kill count.
     */
    @Override
    public int getKillCount() {
        return entitiesManager.getKillCount();
    }

    /**
     * Places a plant of the specified type at the given position,
     * only if the player has enough sun resources.
     *
     * @param type     the {@link PlantType} of the plant to place.
     * @param position the {@link Position} on the grid where the plant should be placed.
     */
    @Override
    public void placePlant(final PlantType type, final Position position) {
        final Plant plant = switch (type) {
            case PEASHOOTER -> plantFactory.createPeashooter(position);
            case SUNFLOWER -> plantFactory.createSunflower(position);
            case WALLNUT -> plantFactory.createWallnut(position);
        };

        if (entitiesManager.decreaseSun(type.getPrice())) { //if plant!=null
            entitiesManager.addEntity(plant);
        }

    }

    /**
     * Utility method to map a domain-level entity to its corresponding {@link EntityType}
     * for external representation (e.g., rendering).
     *
     * @param entity the entity to evaluate.
     * @return the corresponding {@link EntityType}.
     * @throws IllegalArgumentException if the entity type is not recognized.
     */
    private static EntityType getEntityType(final Entity entity) {
        return switch (entity) {
            case Plant plant -> switch (plant.mapToEntityType()) {
                case PEASHOOTER -> EntityType.PEASHOOTER;
                case SUNFLOWER -> EntityType.SUNFLOWER;
                case WALLNUT -> EntityType.WALLNUT;
            };
            case Zombie zombie -> EntityType.ZOMBIE;
            case Bullet bullet -> EntityType.BULLET;
            default -> throw new IllegalArgumentException();
        };
    }
}
