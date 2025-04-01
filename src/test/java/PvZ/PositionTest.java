package PvZ;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantType;
import PvZ.model.impl.Plants.PlantFactory;
import PvZ.utilities.Position;

public class PositionTest {
    @Test
    public void testPeaShooterPosition(){
        Position pos = new Position(3, 4);
        BasePlant peaShooter = PlantFactory.createPlant(PlantType.PEASHOOTER, pos);
        assertEquals(pos, peaShooter.getPosition());
    }

    @Test
    public void testSunflowerPosition(){
        Position pos = new Position(1, 1);
        BasePlant sunflower = PlantFactory.createPlant(PlantType.SUNFLOWER, pos);
        assertEquals(pos, sunflower.getPosition());
    }

    @Test
    public void testWallnutPosition(){
        Position pos = new Position(5, 3);
        BasePlant wallnut = PlantFactory.createPlant(PlantType.WALLNUT, pos);
        assertEquals(pos, wallnut.getPosition());
    }
}
