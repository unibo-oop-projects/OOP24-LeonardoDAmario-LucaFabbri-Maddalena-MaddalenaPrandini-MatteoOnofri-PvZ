package PvZ;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantType;
import PvZ.model.impl.Plants.PlantFactory;
import PvZ.utilities.Position;

public class LifeTest {

    @Test
    public void testDecreasLife(){
        BasePlant peashooter = PlantFactory.createPlant(PlantType.PEASHOOTER, new Position(0, 0));
        peashooter.decreaseLife(30);
        assertEquals(70, peashooter.getLife());
    }

    @Test
    public void testDecreasNegative(){
        BasePlant Wallnut = PlantFactory.createPlant(PlantType.WALLNUT, new Position(0, 0));
        Wallnut.decreaseLife(350);
        assertEquals(-150, Wallnut.getLife());
    }

}
