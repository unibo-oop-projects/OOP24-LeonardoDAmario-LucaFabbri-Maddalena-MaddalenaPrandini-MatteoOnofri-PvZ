package PvZ.model.impl.Plants;


import PvZ.model.api.EntitiesManager;
import PvZ.model.api.PlantType;
import PvZ.model.impl.BulletImpl;

public class PeaShooter extends AbstractPlant{
    EntitiesManager entitiesManager;
    public PeaShooter() {
        super(PlantType.PEASHOOTER);
    }

    @Override
    protected void action() {
        this.entitiesManager.addEntity(new BulletImpl(this.getPosition()));
    }
    
}
