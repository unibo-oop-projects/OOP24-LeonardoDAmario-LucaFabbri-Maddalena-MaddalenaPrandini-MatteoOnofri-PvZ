package PvZ;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import PvZ.model.api.BasePlant;
import PvZ.model.impl.PlantFactory;
import PvZ.model.impl.PlantFactory.PLANT_NAMES;
import PvZ.utilities.Position;

public class PlantDeath {

    @Test
    public void testPlantDeath(){
        BasePlant peashooter = PlantFactory.createPlant(PLANT_NAMES.PEASHOOTER, new Position(0, 0));
        peashooter.decreaseLife(100);
        assertEquals(0, peashooter.getLife());
    }
}
