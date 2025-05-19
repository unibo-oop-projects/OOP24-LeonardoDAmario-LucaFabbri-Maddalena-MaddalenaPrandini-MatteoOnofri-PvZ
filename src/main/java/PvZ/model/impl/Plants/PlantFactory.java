package PvZ.model.impl.Plants;
import PvZ.model.api.Entities.EntitiesManager;
import PvZ.model.api.Plants.PlantType;
import PvZ.model.impl.Bullets.BulletImpl;
import PvZ.model.impl.Collisions.CollisionManagerImpl;
import PvZ.model.api.Plants.Plant;
import PvZ.utilities.Position;

import java.nio.file.OpenOption;
import java.util.Objects;
import java.util.Optional;

import PvZ.model.api.Zombie;
import PvZ.model.api.Collisions.CollisionManager;

/**
 * Factory class for creating plants.
 */
public final class PlantFactory {

    public Plant createPeashooter(Position position) {
        Objects.requireNonNull(position, "Position cannot be null");
        return new AbstractPlant(position){
            @Override
            public PlantType mapToEntityType() {
                return PlantType.PEASHOOTER;
            }

            private final static double FIRE_RATE = 2000;

            private double elapsedTime;

            @Override
            public void update(long deltaTime, EntitiesManager entitiesManager) {
                elapsedTime = elapsedTime + deltaTime;
                if(elapsedTime >= FIRE_RATE && getLife() > 0){
                    entitiesManager.addEntity(new BulletImpl(new Position(this.getPosition().x() +  3 * this.getHitBox().getWidth(), this.getPosition().y())));
                    elapsedTime = 0;
                }
            }

            @Override
            protected int getMaxLife() {
                return PlantType.PEASHOOTER.getLife();
            }

            
        };
    }

    public Plant createSunflower(Position position) {
        Objects.requireNonNull(position, "Position cannot be null");
        return new AbstractPlant(position){
            @Override
            public PlantType mapToEntityType() {
                return PlantType.SUNFLOWER;
            }

            private final static int SUN_VALUE = 25;
            private static final long SUN_GENERATION_INTERVAL = 7000; // ogni 7 secondi
            private long lastSunTime = 0;


            @Override
            public void update(long deltaTime, EntitiesManager entitiesManager) {
                lastSunTime += deltaTime;
                if (lastSunTime >= SUN_GENERATION_INTERVAL) {
                    entitiesManager.addSun(SUN_VALUE); // o quanto vuoi
                    lastSunTime = 0;
                    System.out.println("[SUNFLOWER] Generated 25 sun");
                }
            }

            @Override
            protected int getMaxLife() {
                return PlantType.SUNFLOWER.getLife();
            }
        };
    }

    public Plant createWallnut(Position position) {
        Objects.requireNonNull(position, "Position cannot be null");
        return new AbstractPlant(position){

            private final CollisionManager collisionManager = new CollisionManagerImpl();

            @Override
            public PlantType mapToEntityType() {
                return PlantType.WALLNUT;
            }

            @Override
            public void update(long deltaTime, EntitiesManager entitiesManager) {
                final Optional<Zombie> zombie = collisionManager.handleCollision(this, entitiesManager).map(entity -> (Zombie) entity);
                if(zombie.isPresent()) {
                    entitiesManager.removeEntity(zombie.get());
                    entitiesManager.removeEntity(this);
                    entitiesManager.addKill();
                }
            }

            @Override
            protected int getMaxLife() {
                return PlantType.WALLNUT.getLife();
            }
        };
    }
}
