package pvz.model.impl;
import pvz.model.api.*;
import pvz.model.api.Bullets.Bullet;
import pvz.model.api.Entities.EntitiesManager;
import pvz.model.api.Entities.Entity;
import pvz.model.api.plants.Plant;
import pvz.model.api.plants.PlantType;
import pvz.model.impl.Entitities.EntitiesManagerImpl;
import pvz.model.impl.plants.PlantFactory;
import pvz.utilities.EntityType;
import pvz.utilities.GameEntity;
import pvz.utilities.Position;

import java.util.*;
import java.util.stream.Collectors;

public class GameModelImpl implements GameModel {
    EntitiesManager entitiesManager;
    PlantFactory plantFactory;
    Plant plant;

    private static final int ROWS = 5;
    private static final int COLS = 9;

    private GameStatus status;


    public GameModelImpl() {
        this.entitiesManager = new EntitiesManagerImpl();
        this.plantFactory = new PlantFactory();
        this.status = GameStatus.IN_PROGRESS;

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


        if(plant!=null && entitiesManager.decreaseSun(type.getPrice())){
            entitiesManager.addEntity(plant);
        }

    }
    
    

}