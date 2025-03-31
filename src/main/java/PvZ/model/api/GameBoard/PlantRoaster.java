package PvZ.model.api.GameBoard;

import PvZ.model.impl.Plant;
import java.util.List;

/**
 * This interface defines the methods for a Plant Roaster.
 */
public interface PlantRoaster {

    /**
     * Get a list of available plants.
     * 
     * @param sun the amount of sun available
     * @return a list of available plants
     */
    List<Plant> getAvailablePlants(int sun);

    /**
     * Add a new plant to the roaster.
     * 
     * @param plant to be added
     * @throws IllegalArgumentException if the plant is null
     */
    void addPlant(Plant plant);
}
