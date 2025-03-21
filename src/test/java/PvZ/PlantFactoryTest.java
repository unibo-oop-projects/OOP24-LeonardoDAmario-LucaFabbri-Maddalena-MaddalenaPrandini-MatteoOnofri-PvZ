package PvZ;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import PvZ.model.api.PlantActionStrategy;
import PvZ.model.api.BasePlant;
import PvZ.model.impl.PlantFactory;
import PvZ.utilities.Position;

public class PlantFactoryTest {

    @Test
    public void testCreatePeaShooter() {
        BasePlant peaShooter = PlantFactory.createPlant("PeaShooter", new Position(0, 0));
        assertEquals(100,peaShooter.getLife());
    }

    @Test
    public void testCreateSunFlower() {
        BasePlant sunFlower = PlantFactory.createPlant("SunFlower", new Position(1, 1));
        assertEquals(80,sunFlower.getLife());
    }

    @Test
    public void testCreateWallNut() {
        BasePlant wallNuts = PlantFactory.createPlant("PeaShooter", new Position(2, 2));
        assertEquals(200,wallNuts.getLife());
    }
}
