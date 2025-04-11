package PvZ.model.impl.Plants;


import PvZ.model.api.EntitiesManager;
import PvZ.model.api.PlantType;
import PvZ.model.impl.BulletImpl;
import PvZ.utilities.Position;

public class PeaShooter extends AbstractPlant{
    private double elapsedTime=0;
    private final double fireRate = 2.0;
    public PeaShooter() {
        super(PlantType.PEASHOOTER);
    }

    @Override
    protected void action(long deltaTime, EntitiesManager entitiesManager) {
        elapsedTime+=deltaTime;
        if(elapsedTime>=fireRate){
            entitiesManager.addEntity(new BulletImpl(this.getPosition()));
            elapsedTime=0;
        }
    }
}
