package PvZ.model.impl.Plants;

import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantActionStrategy;
import PvZ.model.impl.BulletImpl;
public class PeaShooterStrategy implements PlantActionStrategy {
    private final int INITIAL_LIFE=100;

    @Override
    public void plantAction(BasePlant plant) {
        new BulletImpl(plant.getPosition());

    }

    @Override
    public int getInitialLife() {
        return this.INITIAL_LIFE;
    }

}
