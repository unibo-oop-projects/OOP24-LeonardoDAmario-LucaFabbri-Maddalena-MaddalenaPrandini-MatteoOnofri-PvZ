package PvZ.model.impl;

import java.util.Objects;
import PvZ.model.api.BasePlant;
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
     * @throws IllegalArgumentException if the plant type is invalid
     * @throws NullPointerException if the position is null
     * @return the created plant
     */
    public static BasePlant createPlant(final PlantType type, final Position position) {
        Objects.requireNonNull(position, "Position cannot be null");
        final BasePlant plant = switch(type) {
            case PEASHOOTER -> new Plant(new PeaShooterStrategy());
            case SUNFLOWER -> new Plant(new SunflowerStrategy());
            case WALLNUT -> new Plant(new WallNutStrategy());
            default -> throw new IllegalArgumentException("Invalid plant type: " + type);
        };    
        plant.setPosition(position);
        return plant;
    }
}
