package PvZ.model.impl;
import PvZ.model.api.*;
import PvZ.model.api.Bullets.Bullet;
import PvZ.model.api.Entities.EntitiesManager;
import PvZ.model.api.Entities.Entity;
import PvZ.model.api.Plants.PlantType;
import PvZ.model.api.Plants.Plant;
import PvZ.model.impl.Entitities.EntitiesManagerImpl;
import PvZ.model.impl.Plants.PlantFactory;
import PvZ.utilities.EntityType;
import PvZ.utilities.GameEntity;
import PvZ.utilities.Position;

import java.util.*;
import java.util.stream.Collectors;

public class GameModelImpl implements GameModel {
    EntitiesManager entitiesManager;
    PlantFactory plantFactory;
    Plant plant;

    private static final int ROWS = 5;
    private static final int COLS = 9;

    private final List<List<Cell>> grid;

    private GameStatus status;

    private long lastSunTime;
    private long lastZombieSpawnTime;

    public GameModelImpl() {
        this.entitiesManager = new EntitiesManagerImpl();
        this.plantFactory = new PlantFactory();

        this.grid = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < COLS; j++) {
                row.add(new Cell());
            }
            grid.add(row);
        }
        this.status = GameStatus.IN_PROGRESS;
        this.lastSunTime = System.currentTimeMillis();
        this.lastZombieSpawnTime = System.currentTimeMillis();
    }

    @Override
    public void startGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startGame'");
    }

    @Override
    public boolean isGameOver() {
            return status != GameStatus.IN_PROGRESS;
    }

    @Override
    public boolean isVictory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isVictory'");
    }

    @Override
    public GameStatus getGameStatus() {
        return status;
    }


    @Override
    public void update(long deltaTime) {
        this.entitiesManager.spawnZombie(deltaTime);
        this.entitiesManager.getEntities().forEach(e->e.update(deltaTime, entitiesManager));
        
    }

    @Override
    public Set<GameEntity> getGameEntities() {
        return entitiesManager.getEntities().stream()
                .map( e -> new GameEntity(getEntityType(e), e.getPosition()))
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

    private static EntityType getEntityType(Entity entity) {
        return switch (entity) {
            case Plant plant -> switch (plant.mapToEntityType()) {
                    case PEASHOOTER -> EntityType.PEASHOOTER;
                    case SUNFLOWER -> EntityType.SUNFLOWER;
                    case WALLNUT -> EntityType.WALLNUT;
                    default -> throw new IllegalArgumentException();
                };
            case Zombie zombie -> EntityType.ZOMBIE;
            case Bullet bullet -> EntityType.BULLET;
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public void placePlant(PlantType type, Position position) {
        System.out.println("[MODEL] Placing " + type + " at " + position);
        Plant plant = switch (type) {
            case PEASHOOTER -> plantFactory.createPeashooter(position);
            case SUNFLOWER -> plantFactory.createSunflower(position);
            case WALLNUT -> plantFactory.createWallnut(position);
            default -> throw new IllegalArgumentException("Invalid plant type: " + type);
        };

        if(plant!=null){
            entitiesManager.addEntity(plant);
        }

    }
    
    

}