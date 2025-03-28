package PvZ;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.lang.model.SourceVersion;

import org.junit.jupiter.api.Test;

import PvZ.model.api.PlantActionStrategy;
import PvZ.model.api.BasePlant;
import PvZ.model.impl.PlantFactory;
import PvZ.model.impl.PlantFactory.PLANT_NAMES;
import PvZ.utilities.Position;

public class PlantFactoryTest {

    @Test
    public void testCreatePeaShooter() {
        BasePlant peaShooter = PlantFactory.createPlant(PLANT_NAMES.PEASHOOTER, new Position(0, 0));
        assertEquals(100,peaShooter.getLife());
    }

    @Test
    public void testCreateSunFlower() {
        BasePlant sunFlower = PlantFactory.createPlant(PLANT_NAMES.SUNFLOWER, new Position(0, 0));
        assertEquals(80,sunFlower.getLife());
    }

    @Test
    public void testCreateWallNut() {
        BasePlant wallNuts = PlantFactory.createPlant(PLANT_NAMES.WALLNUT, new Position(0, 0));
        assertEquals(200,wallNuts.getLife());
    }
}
