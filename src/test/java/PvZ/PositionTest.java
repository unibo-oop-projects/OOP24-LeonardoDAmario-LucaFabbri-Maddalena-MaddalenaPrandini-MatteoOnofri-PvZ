package PvZ;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import PvZ.model.api.BasePlant;
import PvZ.model.impl.PlantFactory;
import PvZ.utilities.Position;

public class PositionTest {
    @Test
    public void testPeaShooterPosition(){
        Position pos = new Position(3, 4);
        BasePlant peaShooter = PlantFactory.createPlant("PeaShooter", pos);
        assertEquals(pos, peaShooter.getPosition());
    }

    @Test
    public void testSunflowerPosition(){
        Position pos = new Position(1, 1);
        BasePlant sunflower = PlantFactory.createPlant("Sunflower", pos);
        assertEquals(pos, sunflower.getPosition());
    }

    @Test
    public void testWallnutPosition(){
        Position pos = new Position(5, 3);
        BasePlant wallnut = PlantFactory.createPlant("Wallnut", pos);
        assertEquals(pos, wallnut.getPosition());
    }
}
