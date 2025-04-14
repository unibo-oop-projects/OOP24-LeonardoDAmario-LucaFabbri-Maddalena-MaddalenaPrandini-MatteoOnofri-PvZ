package PvZ;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import PvZ.model.impl.Plants.PlantFactory;

public class PlantFactoryTest {

    private final PlantFactory plantFactory;

    public PlantFactoryTest() {
        this.plantFactory = new PlantFactory();
    }

    @Test
    public void testNullPositionThrowsException() {
        Exception exception1 = assertThrows(NullPointerException.class, ()-> plantFactory.createPeashooter(null));
        assertEquals("Position cannot be null", exception1.getMessage());

        Exception exception2 = assertThrows(NullPointerException.class, ()-> plantFactory.createSunflower(null));
        assertEquals("Position cannot be null", exception2.getMessage());

        Exception exception3 = assertThrows(NullPointerException.class, ()-> plantFactory.createWallnut(null));
        assertEquals("Position cannot be null", exception3.getMessage());
    }
    
}
