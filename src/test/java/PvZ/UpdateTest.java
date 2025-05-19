package PvZ;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import PvZ.model.api.Entities.EntitiesManager;
import PvZ.model.api.plants.Plant;
import PvZ.model.impl.Entitities.EntitiesManagerImpl;
import PvZ.model.impl.plants.PlantFactory;
import PvZ.model.impl.zombies.ZombieImpl;
import PvZ.utilities.Position;

/**
 * Test class for the update method of the Plant class.
 * This class tests the behavior of different plants when they are updated.
 */
class UpdateTest {
    private final PlantFactory plantFactory;
    private final EntitiesManager entitiesManager;

     UpdateTest() {
        this.plantFactory = new PlantFactory();
        this.entitiesManager = new EntitiesManagerImpl();
    }

    /**
     * Test the update method of Peashooter.
     * It checks if the Peashooter is added to the entities manager after a certain time.
     */

    @Test
     void testPeashooterUpdate() {
        final long delta_time = 1000L;
        final Position position = new Position(1, 1);
        final Plant peashooter = plantFactory.createPeashooter(position);

        peashooter.update(1L, entitiesManager);
        assertEquals(0, entitiesManager.getEntities().size());

        peashooter.update(delta_time, entitiesManager);
        assertEquals(1, entitiesManager.getEntities().size());

        peashooter.decreaseLife(peashooter.getLife());
        peashooter.update(delta_time * 2, entitiesManager);
        assertEquals(1, entitiesManager.getEntities().size());
    }

    /**
     * Test the update method of Sunflower.
     * It checks if the Sunflower generates sun after a certain time.
     */
    @Test
     void testSunflowerUpdate() {
        final long delta_time = 4000L;
        final int sun_value = 25;
        final Position pos = new Position(2, 2);
        final Plant sunflower = plantFactory.createSunflower(pos);
        final int initialSun = entitiesManager.getSunCount();

        sunflower.update(delta_time / 4, entitiesManager);
        assertEquals(initialSun, entitiesManager.getSunCount());

        sunflower.update(delta_time, entitiesManager);
        assertEquals(initialSun + sun_value, entitiesManager.getSunCount());

        sunflower.decreaseLife(sunflower.getLife());
        sunflower.update(delta_time, entitiesManager);
        assertEquals(initialSun + sun_value, entitiesManager.getSunCount());
    }

    /**
     * Test the update method of Wallnut.
     * It checks if the Wallnut is removed from the entities manager after being attacked by a zombie.
     */
    @Test
     void testWallnutUpdate() {
        final Position pos = new Position(3, 3);
        final Plant wallnut = plantFactory.createWallnut(pos);

        entitiesManager.addEntity(wallnut);
        wallnut.update(1L, entitiesManager);
        assertEquals(1, entitiesManager.getEntities().size());

        final ZombieImpl zombie = new ZombieImpl(pos, 50, 1, null);
        entitiesManager.addEntity(zombie);

        assertEquals(2, entitiesManager.getEntities().size());

        wallnut.update(1L, entitiesManager);
        assertEquals(0, entitiesManager.getEntities().size());
    }
}
