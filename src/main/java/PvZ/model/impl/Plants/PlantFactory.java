package PvZ.model.impl.Plants;
import PvZ.model.api.Entities.EntitiesManager;
import PvZ.model.api.Plants.PlantType;
import PvZ.model.impl.Bullets.BulletImpl;
import PvZ.model.api.Plants.Plant;
import PvZ.utilities.Position;
import java.util.Objects;

/**
 * Factory class for creating plants.
 */
public final class PlantFactory {

    public Plant createPeashooter(Position position) {
        Objects.requireNonNull(position, "Position cannot be null");
        return new AbstractPlant(position){
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
            private final static int SUN_VALUE = 25;
            private final static double GENERATED_SUN = 2.5;

            private double elapsedTime;

            @Override
            public void update(long deltaTime, EntitiesManager entitiesManager) {
                elapsedTime = elapsedTime + deltaTime;
                if(elapsedTime >= GENERATED_SUN && getLife() > 0){
                    entitiesManager.addSun(SUN_VALUE);
                    elapsedTime = 0;
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
            public void update(long deltaTime, EntitiesManager entitiesManager) {
                // Wallnut does not do anything
            }

            @Override
            protected int getMaxLife() {
                return PlantType.WALLNUT.getLife();
            }
        };
    }
}
