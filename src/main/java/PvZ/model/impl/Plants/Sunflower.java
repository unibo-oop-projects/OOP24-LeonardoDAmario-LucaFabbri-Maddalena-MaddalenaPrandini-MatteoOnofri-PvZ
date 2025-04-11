package PvZ.model.impl.Plants;

import PvZ.model.api.EntitiesManager;
import PvZ.model.api.PlantType;

public class Sunflower extends AbstractPlant{
    EntitiesManager entitiesManager;
    private final int SUN_VALUE = 25;
    public Sunflower() {
        super(PlantType.SUNFLOWER);
    }

    @Override
    protected void action() {
        this.entitiesManager.addSun(this.SUN_VALUE);
    }

}
