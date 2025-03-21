package PvZ;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import PvZ.model.api.BasePlant;
import PvZ.model.impl.Plant;
import PvZ.model.impl.PlantFactory;
import PvZ.utilities.Position;

public class LifeTest {

    @Test
    public void testDecreasLife(){
        BasePlant peashooter = PlantFactory.createPlant("PeaShooter", new Position(0, 0));
        peashooter.decreaseLife(30);
        assertEquals(70, peashooter.getLife());
    }

    @Test
    public void testDecreasNegative(){
        BasePlant Wallnut = PlantFactory.createPlant("Wallnut", new Position(3, 2));
        Wallnut.decreaseLife(350);
        assertEquals(-150, Wallnut.getLife());
    }

}
