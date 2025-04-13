package PvZ.model.impl.Plants;

import PvZ.model.api.EntitiesManager;
import PvZ.model.api.PlantType;

public class WallNut extends AbstractPlant {

    public WallNut() {
        super(PlantType.WALLNUT);
    }

    @Override
    protected void action(long deltaTime, EntitiesManager entitiesManager) {

    }
}
