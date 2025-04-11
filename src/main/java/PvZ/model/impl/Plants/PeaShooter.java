package PvZ.model.impl.Plants;


import PvZ.model.api.PlantType;
import PvZ.model.impl.BulletImpl;

public class PeaShooter extends AbstractPlant{
    
    public PeaShooter() {
        super(PlantType.PEASHOOTER);
    }

    @Override
    protected void action() {
        new BulletImpl(this.position);
    }
    
}
