package PvZ;

import org.junit.jupiter.api.Test;

import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantActionStrategy;
import PvZ.model.impl.Plant;
import PvZ.utilities.Position;

public class PlantActionTest {

    private static class TryStrategy implements PlantActionStrategy{
        private boolean actionCalled = false;
        private final int initialLife;

        public TryStrategy(int initialLife){
            this.initialLife = initialLife;
        }   

        @Override
        public void plantAction(BasePlant plant) {
            actionCalled = true;
        }       
        @Override
        public int getInitialLife() {
            return this.initialLife;
        }

        public boolean isActionCalled() {
            return actionCalled;
        }

    }
    @Test
        public void testPlantAction() {
            TryStrategy strategy = new TryStrategy(50);
            BasePlant plant = new Plant(strategy);
            plant.setPosition(new Position(0, 0));
            plant.plantAction();
            assert(strategy.isActionCalled());
        }

}
