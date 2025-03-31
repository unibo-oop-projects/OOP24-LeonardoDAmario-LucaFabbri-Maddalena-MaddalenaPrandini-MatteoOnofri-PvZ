package PvZ;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantActionStrategy;
import PvZ.model.api.PlantType;
import PvZ.model.impl.Plant;
import PvZ.utilities.Position;

public class UpdateTest {
    
    private static class UpdateLifeStrategy implements PlantActionStrategy{
        private boolean actionCalled=false;
        private final int initialLife;

        public UpdateLifeStrategy(int initialLife){
            this.initialLife=initialLife;
        }

        @Override       
        public void plantAction(BasePlant plant) {
            actionCalled=true;
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
    public void testPlantUpdateLife() {
        UpdateLifeStrategy strategy = new UpdateLifeStrategy(10);
        BasePlant plant = new Plant(strategy, PlantType.PEASHOOTER, new Position(0, 0));
        plant.decreaseLife(10);
        plant.update();
        assertTrue(strategy.isActionCalled(), "action should be called during update");
    }
}
