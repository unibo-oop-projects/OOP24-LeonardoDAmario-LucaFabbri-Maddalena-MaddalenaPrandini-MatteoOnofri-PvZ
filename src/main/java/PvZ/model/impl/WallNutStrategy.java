package PvZ.model.impl;

import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantActionStrategy;

public class WallNutStrategy implements PlantActionStrategy {
    private final int INITIAL_LIFE=200;

    @Override
    public void plantAction(BasePlant plant) {
        /*da mettere metodo per dimezzare la vita */
    }

    @Override
    public int getInitialLife() {
        return this.INITIAL_LIFE;
    }

}
