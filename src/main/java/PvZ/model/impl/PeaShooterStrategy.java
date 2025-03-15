package PvZ.model.impl;

import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantActionStrategy;

public class PeaShooterStrategy implements PlantActionStrategy {
    private final int CONST=100;
    @Override
    public void PlantAction(BasePlant plant) {
        BulletImpl bullet = new BulletImpl(plant.getPosition());
    }

    @Override
    public int getInitialLife() {
        return this.CONST;
    }

}
