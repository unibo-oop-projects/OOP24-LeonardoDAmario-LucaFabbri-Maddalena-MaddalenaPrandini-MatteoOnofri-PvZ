package pvz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pvz.model.api.Bullets.Bullet;
import pvz.model.api.Difficulty;
import pvz.model.api.Zombie;
import pvz.model.api.entities.EntitiesManager;
import pvz.model.api.plants.Plant;
import pvz.model.impl.Bullets.BulletImpl;
import pvz.model.impl.Collisions.CollisionManagerImpl;
import pvz.model.impl.entities.EntitiesManagerImpl;
import pvz.model.impl.plants.PlantFactory;
import pvz.model.impl.zombies.ZombieImpl;
import pvz.utilities.Position;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class CollisionsTest {

    private CollisionManagerImpl collisionManager;
    private EntitiesManager entitiesManager;
    private PlantFactory plantFactory;

    @BeforeEach
    void setUp() {
        collisionManager = new CollisionManagerImpl();
        entitiesManager = new EntitiesManagerImpl(Difficulty.NORMAL);
        plantFactory = new PlantFactory();
    }

    @Test
    void testBulletZombieCollision() {
        Bullet bullet = new BulletImpl(new Position(100, 50));
        Zombie zombie = new ZombieImpl(new Position(100, 50), 100, 10);
        entitiesManager.addEntity(bullet);
        entitiesManager.addEntity(zombie);
        Optional<?> result = collisionManager.handleCollision(bullet, entitiesManager);
        assertTrue(result.isPresent(), "La collisione tra bullet e zombie dovrebbe essere rilevata");
        assertEquals(zombie, result.get(), "L'entità collisionata dovrebbe essere lo zombie");
    }

    @Test
    void testBulletZombieNoCollisionDifferentY() {
        Bullet bullet = new BulletImpl(new Position(100, 50));
        Zombie zombie = new ZombieImpl(new Position(100, 60), 100, 10);
        entitiesManager.addEntity(bullet);
        entitiesManager.addEntity(zombie);
        Optional<?> result = collisionManager.handleCollision(bullet, entitiesManager);
        assertFalse(result.isPresent(), "Non dovrebbe esserci collisione tra bullet e zombie su y diverse");
    }

    @Test
    void testBulletZombieNoEntities() {
        Bullet bullet = new BulletImpl(new Position(100, 50));
        Optional<?> result = collisionManager.handleCollision(bullet, entitiesManager);
        assertFalse(result.isPresent(), "Non dovrebbe esserci collisione senza entità");
    }

    @Test
    void testZombiePlantCollision() {
        Zombie zombie = new ZombieImpl(new Position(100, 50), 100, 10);
        Plant plant = plantFactory.createPeashooter(new Position(100, 50));
        entitiesManager.addEntity(zombie);
        entitiesManager.addEntity(plant);
        Optional<?> result = collisionManager.handleCollision(zombie, entitiesManager);
        assertTrue(result.isPresent(), "La collisione tra zombie e pianta dovrebbe essere rilevata");
        assertEquals(plant, result.get(), "L'entità collisionata dovrebbe essere la pianta");
    }

    @Test
    void testZombiePlantNoCollisionDifferentY() {
        Zombie zombie = new ZombieImpl(new Position(100, 50), 100, 10);
        Plant plant = plantFactory.createPeashooter(new Position(100, 60));
        entitiesManager.addEntity(zombie);
        entitiesManager.addEntity(plant);
        Optional<?> result = collisionManager.handleCollision(zombie, entitiesManager);
        assertFalse(result.isPresent(), "Non dovrebbe esserci collisione tra zombie e pianta su y diverse");
    }

    @Test
    void testZombiePlantNoEntities() {
        Zombie zombie = new ZombieImpl(new Position(100, 50), 100, 10);
        Optional<?> result = collisionManager.handleCollision(zombie, entitiesManager);
        assertFalse(result.isPresent(), "Non dovrebbe esserci collisione senza entità");
    }

    @Test
    void testWallnutZombieCollision() {
        Plant wallnut = plantFactory.createWallnut(new Position(100, 50));
        Zombie zombie = new ZombieImpl(new Position(100, 50), 100, 10);
        entitiesManager.addEntity(wallnut);
        entitiesManager.addEntity(zombie);
        Optional<?> result = collisionManager.handleCollision(wallnut, entitiesManager);
        assertTrue(result.isPresent(), "La collisione tra wallnut e zombie dovrebbe essere rilevata");
        assertEquals(zombie, result.get(), "L'entità collisionata dovrebbe essere lo zombie");
    }

    @Test
    void testWallnutZombieNoCollisionDifferentY() {
        Plant wallnut = plantFactory.createWallnut(new Position(100, 50));
        Zombie zombie = new ZombieImpl(new Position(100, 60), 100, 10);
        entitiesManager.addEntity(wallnut);
        entitiesManager.addEntity(zombie);
        Optional<?> result = collisionManager.handleCollision(wallnut, entitiesManager);
        assertFalse(result.isPresent(), "Non dovrebbe esserci collisione tra wallnut e zombie su y diverse");
    }

    @Test
    void testWallnutZombieNoEntities() {
        Plant wallnut = plantFactory.createWallnut(new Position(100, 50));
        Optional<?> result = collisionManager.handleCollision(wallnut, entitiesManager);
        assertFalse(result.isPresent(), "Non dovrebbe esserci collisione senza entità");
    }
}