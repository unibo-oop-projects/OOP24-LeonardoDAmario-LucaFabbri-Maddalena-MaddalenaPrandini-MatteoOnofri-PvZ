package pvz.model.zombies.api;

import pvz.model.entities.api.Entity;
import pvz.utilities.Position;

/**
 * Interface representing the base functionality of a zombie in the game.
 * Extends the generic {@link Entity} interface.
 */
public interface Zombie extends Entity {

    /**
     * Decreases the zombie's life by the specified amount of damage.
     *
     * @param damage The damage to apply.
     */
    void decreaseLife(final int damage);
   
    /**
     * Gets the damage dealt by this zombie.
     *
     * @return The damage value.
     */
    int getDamage();

    /**
     * Gets the current health points of the zombie.
     *
     * @return The remaining health of the zombie.
     */    
    int getHealth();

    /**
     * Gets the speed of the zombie.
     *
     * @return The speed value.
     */
    int getSpeed();

    /**
     * Applies damage to the zombie.
     *
     * @param damage The amount of damage to apply.
     */
    void takeDamage(int damage);

    /**
     * Checks if the zombie is still alive.
     *
     * @return true if the zombie is alive, false otherwise.
     */
    boolean isAlive();

    /**
     * Sets the position of the zombie.
     *
     * @param position The new position.
     */
    void setPosition(Position position);

    /**
     * Gets the current position of the zombie.
     *
     * @return The current position.
     */
    Position getPosition();

    /**
     * Instantly kills the zombie, regardless of its current health.
     */
    void forceKill();

    /**
     * Gets the type of this zombie as a {@link ZombieType}.
     *
     * @return The zombie type corresponding to the implementation.
     */
    ZombieType getType();
}
