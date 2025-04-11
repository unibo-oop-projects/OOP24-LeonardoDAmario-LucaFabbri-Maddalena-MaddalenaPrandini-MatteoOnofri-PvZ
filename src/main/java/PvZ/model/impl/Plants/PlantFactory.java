package PvZ.model.impl.Plants;
import PvZ.model.api.Plant;
import PvZ.model.api.PlantType;
import PvZ.utilities.Position;

/**
 * Factory class for creating plants.
 */
public final class PlantFactory {

    /**
     * Creates a plant of the specified type at the given position.
     * 
     * @param type the type of plant to create
     * @throws IllegalArgumentException if the plant type is invalid
     * @return the created plant
     */
    public static Plant createPlant(final PlantType type) {
        return switch(type) {
            case PEASHOOTER -> new PeaShooter();
            case SUNFLOWER -> new Sunflower();
            case WALLNUT -> new WallNut();

            default-> throw new IllegalArgumentException();
        };    
    }
}
