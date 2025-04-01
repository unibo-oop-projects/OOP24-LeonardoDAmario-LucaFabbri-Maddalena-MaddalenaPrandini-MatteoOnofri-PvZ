package PvZ;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantType;
import PvZ.model.impl.Plants.PlantFactory;
import PvZ.utilities.Position;

public class PlantDeath {

    @Test
    public void testPlantDeath(){
        BasePlant peashooter = PlantFactory.createPlant(PlantType.PEASHOOTER, new Position(0, 0));
        peashooter.decreaseLife(100);
        assertEquals(0, peashooter.getLife());
    }
}
