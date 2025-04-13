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
     * @param position the position of the plant
     * @throws NullPointerException if the type or position is null
     * @throws IllegalArgumentException if the plant type is invalid
     * @return the created plant
     */
    public static Plant createPlant(final PlantType type, Position position) {
        
        if(type == null) {
            throw new IllegalArgumentException("Plant type cannot be null");
        }

        if(position == null) {
            throw new IllegalArgumentException("Position cannot be null");
        }

        Plant plant = switch(type) {
            case PEASHOOTER -> new PeaShooter();
            case SUNFLOWER -> new Sunflower();
            case WALLNUT -> new WallNut();
            default-> throw new IllegalArgumentException("unsupported type: " + type);
        };
        plant.setPosition(position);
        return plant;

    }
}
