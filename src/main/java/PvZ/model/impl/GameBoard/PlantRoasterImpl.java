package PvZ.model.impl.GameBoard;
import PvZ.model.api.EntitiesManager;
import PvZ.model.api.PlantType;
import PvZ.model.api.GameBoard.PlantRoaster;
import PvZ.model.impl.Plants.PlantFactory;
import PvZ.utilities.Position;
/**
 * This class implements the PlantRoaster interface.
 */
public final class PlantRoasterImpl implements PlantRoaster {

    private final EntitiesManager entitiesManager;

    public PlantRoasterImpl(EntitiesManager entitiesManager) {
        this.entitiesManager = entitiesManager;
    }

    @Override
    public void addPlantGame(PlantType plantType, Position position) {
        entitiesManager.addEntity(PlantFactory.createPlant(plantType, position));
    }

    
    
}
