package PvZ.model.impl.Plants;
import PvZ.model.api.Entities.EntitiesManager;
import PvZ.model.api.Plants.PlantType;
import PvZ.model.impl.Bullets.BulletImpl;
import PvZ.model.api.Plants.Plant;
import PvZ.utilities.Position;
import java.util.Objects;
import PvZ.model.api.Zombie;

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

            private final static double FIRE_RATE = 1.5;

            private double elapsedTime;

            @Override
            public void update(long deltaTime, EntitiesManager entitiesManager) {
                elapsedTime = elapsedTime + deltaTime;
                if(elapsedTime >= FIRE_RATE && getLife() > 0){
                    entitiesManager.addEntity(new BulletImpl(position));
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

            private double elapsedTime;

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

            @Override
            public PlantType mapToEntityType() {
                return PlantType.WALLNUT;
            }

            @Override
            public void update(long deltaTime, EntitiesManager entitiesManager) {
                entitiesManager.getEntities().stream()
                        .filter(e -> e instanceof Zombie)
                        .filter(z -> z.getHitBox().isColliding(this.getHitBox()))
                        .findAny()
                        .ifPresent(z->{
                            entitiesManager.removeEntity(z);
                            entitiesManager.removeEntity(this);
                        });
            }

            @Override
            protected int getMaxLife() {
                return PlantType.WALLNUT.getLife();
            }
        };
    }
}
