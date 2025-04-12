package PvZ.model.api.GameBoard;

import PvZ.model.api.PlantType;
import PvZ.utilities.Position;

/**
 * This interface defines the methods for a Plant Roaster.
 */
public interface PlantRoaster {
    public void addPlantGame(PlantType plantType, Position position);
}

