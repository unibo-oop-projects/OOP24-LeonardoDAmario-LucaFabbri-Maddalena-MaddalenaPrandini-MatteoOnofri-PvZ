package pvz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import pvz.model.api.Difficulty;
import pvz.model.api.entities.EntitiesManager;
import pvz.model.api.plants.Plant;
import pvz.model.impl.entities.EntitiesManagerImpl;
import pvz.model.impl.plants.PlantFactory;
import pvz.model.impl.zombies.ZombieImpl;
import pvz.utilities.Position;

/**
 * Test class for the update method of the Plant class.
 * This class tests the behavior of different plants when they are updated.
 */
class UpdateTest {
    private static final long DELTA_TIME = 1000L;
    private static final int SUN_VALUE = 25;
    private final PlantFactory plantFactory;
    private final EntitiesManager entitiesManager;

     UpdateTest() {
        this.plantFactory = new PlantFactory();
        this.entitiesManager = new EntitiesManagerImpl(Difficulty.EASY);
    }

    /**
     * Test the update method of Peashooter.
     * It checks if the Peashooter is added to the entities manager after a certain time.
     */

    @Test
     void testPeashooterUpdate() {
        final Position position = new Position(1, 1);
        final Plant peashooter = plantFactory.createPeashooter(position);

        peashooter.update(1L, entitiesManager);
        assertEquals(0, entitiesManager.getEntities().size());

        peashooter.update(DELTA_TIME, entitiesManager);
        assertEquals(1, entitiesManager.getEntities().size());

        peashooter.decreaseLife(peashooter.getLife());
        peashooter.update(DELTA_TIME * 2, entitiesManager);
        assertEquals(1, entitiesManager.getEntities().size());
    }

    /**
     * Test the update method of Sunflower.
     * It checks if the Sunflower generates sun after a certain time.
     */
    @Test
     void testSunflowerUpdate() {
        final Position pos = new Position(2, 2);
        final Plant sunflower = plantFactory.createSunflower(pos);
        final int initialSun = entitiesManager.getSunCount();

        sunflower.update(DELTA_TIME, entitiesManager);
        assertEquals(initialSun, entitiesManager.getSunCount());

        sunflower.update(DELTA_TIME * 4, entitiesManager);
        assertEquals(initialSun + SUN_VALUE, entitiesManager.getSunCount());

        sunflower.decreaseLife(sunflower.getLife());
        sunflower.update(DELTA_TIME * 4, entitiesManager);
        assertEquals(initialSun + SUN_VALUE, entitiesManager.getSunCount());
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

        final ZombieImpl zombie = new ZombieImpl(pos, 50, 1);
        entitiesManager.addEntity(zombie);

        assertEquals(2, entitiesManager.getEntities().size());

        wallnut.update(1L, entitiesManager);
        assertEquals(0, entitiesManager.getEntities().size());
    }
}
