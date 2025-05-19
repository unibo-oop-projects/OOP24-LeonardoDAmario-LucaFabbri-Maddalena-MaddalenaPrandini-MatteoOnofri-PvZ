package pvz.model.impl.plants;
import pvz.model.api.Entities.EntitiesManager;
import pvz.model.api.plants.PlantType;
import pvz.model.impl.Bullets.BulletImpl;
import pvz.model.impl.Collisions.CollisionManagerImpl;
import pvz.model.api.plants.Plant;
import pvz.utilities.Position;

import java.util.Objects;
import java.util.Optional;

import pvz.model.api.Zombie;
import pvz.model.api.Collisions.CollisionManager;

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

            private final static double FIRE_RATE = 1000;

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
            private static final long SUN_GENERATION_INTERVAL = 4000;
            private long lastSunTime = 0;


            @Override
            public void update(long deltaTime, EntitiesManager entitiesManager) {
                lastSunTime += deltaTime;
                if (lastSunTime >= SUN_GENERATION_INTERVAL) {
                    entitiesManager.addSun(SUN_VALUE);
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
