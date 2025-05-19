package pvz.model.impl.plants;
import pvz.model.api.entities.EntitiesManager;
import pvz.model.api.plants.PlantType;
import pvz.model.impl.Bullets.BulletImpl;
import pvz.model.impl.Collisions.CollisionManagerImpl;
import pvz.model.api.plants.Plant;
import pvz.utilities.Position;

import java.util.Objects;
import java.util.Optional;

import pvz.model.api.Zombie;
import pvz.model.api.Collisions.CollisionManager;

/**
 * Factory class for creating different types of {@link Plant} instances.
 * Each plant has unique behavior implemented via anonymous inner classes.
 */
public final class PlantFactory {

    /**
     * Creates a new {@link PlantType#PEASHOOTER} instance at the given position.
     * The Peashooter periodically shoots bullets if alive.
     *
     * @param position the position where the plant should be placed; must not be null.
     * @return a new {@link Plant} instance representing a Peashooter.
     * @throws NullPointerException if the position is null.
     */
    public Plant createPeashooter(final Position position) {
        Objects.requireNonNull(position, "Position cannot be null");
        return new AbstractPlant(position) {

            private static final double FIRE_RATE = 1000;
            private double elapsedTime;

            @Override
            public PlantType mapToEntityType() {
                return PlantType.PEASHOOTER;
            }

            @Override
            public void update(final long deltaTime, final EntitiesManager entitiesManager) {
                elapsedTime = elapsedTime + deltaTime;
                if (elapsedTime >= FIRE_RATE && getLife() > 0) {
                    entitiesManager.addEntity(
                            new BulletImpl(
                                    new Position(this.getPosition().x() +  3 * this.getHitBox().getWidth(),
                                            this.getPosition().y())));
                    elapsedTime = 0;
                }
            }

            @Override
            protected int getMaxLife() {
                return PlantType.PEASHOOTER.getLife();
            }
        };
    }

    /**
     * Creates a new {@link PlantType#SUNFLOWER} instance at the given position.
     * The Sunflower generates sun points periodically if alive.
     *
     * @param position the position where the plant should be placed; must not be null.
     * @return a new {@link Plant} instance representing a Sunflower.
     * @throws NullPointerException if the position is null.
     */
    public Plant createSunflower(final Position position) {
        Objects.requireNonNull(position, "Position cannot be null");
        return new AbstractPlant(position) {

            private static final int SUN_VALUE = 25;
            private static final long SUN_GENERATION_INTERVAL = 4000;
            private long lastSunTime;

            @Override
            public PlantType mapToEntityType() {
                return PlantType.SUNFLOWER;
            }


            @Override
            public void update(final long deltaTime, final EntitiesManager entitiesManager) {
                if (this.getLife() <= 0) {
                    return;
                }
                lastSunTime += deltaTime;
                if (lastSunTime >= SUN_GENERATION_INTERVAL) {
                    entitiesManager.addSun(SUN_VALUE);
                    lastSunTime = 0;
                }
            }

            @Override
            protected int getMaxLife() {
                return PlantType.SUNFLOWER.getLife();
            }
        };
    }

    /**
     * Creates a new {@link PlantType#WALLNUT} instance at the given position.
     * The Wall-nut checks for collisions with zombies and removes both itself and the zombie upon collision.
     *
     * @param position the position where the plant should be placed; must not be null.
     * @return a new {@link Plant} instance representing a Wall-nut.
     * @throws NullPointerException if the position is null.
     */
    public Plant createWallnut(final Position position) {
        Objects.requireNonNull(position, "Position cannot be null");
        return new AbstractPlant(position) {

            private final CollisionManager collisionManager = new CollisionManagerImpl();

            @Override
            public PlantType mapToEntityType() {
                return PlantType.WALLNUT;
            }

            @Override
            public void update(final long deltaTime, final EntitiesManager entitiesManager) {
                final Optional<Zombie> zombie = collisionManager.handleCollision(this, entitiesManager)
                        .map(entity -> (Zombie) entity);
                if (zombie.isPresent()) {
                    entitiesManager.removeEntity(zombie.get());
                    entitiesManager.removeEntity(this);
                    entitiesManager.addKill();
                }
            }

            @Override
            protected int getMaxLife() {
                return PlantType.WALLNUT.getLife();
            }
        };
    }
}
