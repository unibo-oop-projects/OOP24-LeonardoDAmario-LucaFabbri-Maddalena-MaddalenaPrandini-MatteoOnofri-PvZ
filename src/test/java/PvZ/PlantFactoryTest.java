package PvZ;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import PvZ.model.api.Plant;
import org.junit.jupiter.api.Test;
import PvZ.model.api.PlantType;
import PvZ.model.impl.Plants.PlantFactory;
import PvZ.utilities.Position;

public class PlantFactoryTest {

    @Test
    public void testcreatePeashooterPlant() {
        Position position= new Position(0,0);
        Plant peashooter = PlantFactory.createPlant(PlantType.PEASHOOTER, position);

        assertNotNull(peashooter);
        assertEquals(PlantType.PEASHOOTER.getLife(), peashooter.getLife());
        assertEquals(position,peashooter.getPosition());
    }

    @Test
    public void testcreateSunFlowerPlant(){
        Position position= new Position(0,0);
        Plant sunflower = PlantFactory.createPlant(PlantType.SUNFLOWER, position);

        assertNotNull(sunflower);
        assertEquals(PlantType.SUNFLOWER.getLife(), sunflower.getLife());
        assertEquals(position,sunflower.getPosition());
    }

    @Test
    public void testInvalidPlantType() {
        assertThrows(IllegalArgumentException.class,()-> {
            PlantFactory.createPlant(null, new Position(0, 0));
        });
    }
}
