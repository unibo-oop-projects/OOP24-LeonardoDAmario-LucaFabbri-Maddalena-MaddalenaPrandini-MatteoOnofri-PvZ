package PvZ.model.impl.Plants;
import PvZ.model.api.Plant;
import PvZ.model.api.PlantType;
/**
 * Factory class for creating plants.
 */
public final class PlantFactory {

    /**
     * Creates a plant of the specified type at the given position.
     * 
     * @param type the type of plant to create
     * @param position the position of the plant
     * @throws IllegalArgumentException if the plant type is invalid
     * @throws NullPointerException if the position is null
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
