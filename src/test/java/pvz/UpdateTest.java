package pvz;

import org.junit.jupiter.api.Test;

import pvz.model.game.api.Difficulty;
import pvz.model.entities.api.EntitiesManager;
import pvz.model.plants.api.Plant;
import pvz.model.entities.impl.EntitiesManagerImpl;
import pvz.model.plants.impl.PlantFactory;
import pvz.model.zombies.api.Zombie;
import pvz.model.zombies.impl.BasicZombie;
import pvz.model.zombies.impl.ZombieFactory;
import pvz.utilities.Position;

import static org.junit.jupiter.api.Assertions.*;

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
    void testWallNutUpdate() {
        EntitiesManager entitiesManager = new EntitiesManagerImpl(Difficulty.EASY);
        Position position = new Position(0, 0);

        PlantFactory plantFactory = new PlantFactory();
        Plant wallnut = plantFactory.createWallnut(position);

        Zombie zombie = new BasicZombie(position);

        entitiesManager.addEntity(wallnut);
        entitiesManager.addEntity(zombie);

        wallnut.update(1000, entitiesManager);

        assertFalse(entitiesManager.getEntities().contains(wallnut));
        assertFalse(entitiesManager.getEntities().contains(zombie));
        assertEquals(1, entitiesManager.getKillCount());
    }


}
