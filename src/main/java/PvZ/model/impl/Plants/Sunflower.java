package PvZ.model.impl.Plants;

import PvZ.model.api.EntitiesManager;
import PvZ.model.api.PlantType;

public class Sunflower extends AbstractPlant{
    private final int SUN_VALUE = 25;
    private double elapsedTime=0;
    private final double fireRate = 5.0;
    public Sunflower() {
        super(PlantType.SUNFLOWER);
    }

    @Override
    protected void action(long deltaTime, EntitiesManager entitiesManager) {
        elapsedTime+=deltaTime;
        if(elapsedTime>=fireRate) {
            entitiesManager.addSun(this.SUN_VALUE);
            elapsedTime = 0;
        }
    }
}
