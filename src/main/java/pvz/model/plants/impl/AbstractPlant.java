package pvz.model.plants.impl;

import pvz.model.collisions.impl.HitBoxFactory.HitBoxType;
import pvz.model.game.api.EntitiesManager;
import pvz.model.entities.impl.AbstractEntity;
import pvz.model.plants.api.Plant;
import pvz.utilities.Position;

/**
 * Abstract base class for all plant entities in the game.
 * Implements shared behavior such as life management and hitbox assignment.
 */
public abstract class AbstractPlant extends AbstractEntity implements Plant {

    private int damage;

    /**
     * Constructs an AbstractPlant with the specified position and default hitbox type for plants.
     *
     * @param position The initial position of the plant.
     */
    public AbstractPlant(final Position position) {
        super(position, HitBoxType.PLANT);
    }

    /**
     * Updates the plant logic depending on its specific behavior.
     *
     * @param deltaTime       The time elapsed since the last update.
     * @param entitiesManager The manager responsible for handling entities in the game.
     */
    @Override
    public abstract void update(long deltaTime, EntitiesManager entitiesManager);

    /**
     * Gets the current life of the plant, calculated as max life minus damage received.
     *
     * @return The remaining life of the plant.
     */
    @Override
    public final int getLife() {
        return this.getMaxLife() - this.damage;
    }

    /**
     * Reduces the life of the plant by a specified amount of damage.
     *
     * @param damage The damage to apply to the plant.
     */
    @Override
    public final void decreaseLife(final int damage) {
        this.damage = this.damage + damage;
    }

    /**
     * Gets the maximum life value for the specific type of plant.
     *
     * @return The maximum life of the plant.
     */
    protected abstract int getMaxLife();
}
