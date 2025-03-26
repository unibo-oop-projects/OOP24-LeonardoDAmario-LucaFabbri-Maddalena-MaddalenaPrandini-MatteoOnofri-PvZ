package PvZ.model.api.GameBoard;

import PvZ.model.api.BasePlant;
import java.util.List;

public interface PlantRoaster {

    List<BasePlant> getAvailablePlants();
    void addPlant(BasePlant plant);

}
