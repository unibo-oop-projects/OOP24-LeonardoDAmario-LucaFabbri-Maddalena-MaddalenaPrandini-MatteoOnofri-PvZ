package PvZ.model.impl.Plants;


import PvZ.model.api.EntitiesManager;
import PvZ.model.api.Plant;
import PvZ.model.api.PlantType;
import PvZ.model.impl.AbstractEntity;
import PvZ.model.impl.BulletImpl;

public class PeaShooter extends AbstractPlant{
    public PeaShooter() {
        super(PlantType.PEASHOOTER);
    }

    @Override
    protected void action(EntitiesManager entitiesManager) {
        entitiesManager.addEntity(new BulletImpl(this.getPosition()));
    }
}
