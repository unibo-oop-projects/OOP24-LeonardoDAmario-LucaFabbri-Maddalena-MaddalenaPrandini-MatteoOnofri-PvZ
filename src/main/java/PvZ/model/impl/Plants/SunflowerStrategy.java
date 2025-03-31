package PvZ.model.impl.Plants;

import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantActionStrategy;

public class SunflowerStrategy implements PlantActionStrategy{
    private final int INITIAL_LIFE=80;

    @Override
    public void plantAction(BasePlant plant) {
        /*da mettere metodo che genera soli */
    }

    @Override
    public int getInitialLife() {
        return this.INITIAL_LIFE;
    }


}
