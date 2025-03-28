package PvZ.model.impl;

import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantActionStrategy;

public class PeaShooterStrategy implements PlantActionStrategy {
    private final int INITIAL_LIFE=100;

    @Override
    public void plantAction(BasePlant plant) {
        new BulletImpl(plant.getPosition()).update();
    }

    @Override
    public int getInitialLife() {
        return this.INITIAL_LIFE;
    }

}
