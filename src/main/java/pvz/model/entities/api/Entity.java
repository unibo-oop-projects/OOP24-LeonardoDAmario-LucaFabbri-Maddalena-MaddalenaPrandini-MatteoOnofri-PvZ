package pvz.model.entities.api;

import pvz.model.collisions.api.HitBox;
import pvz.utilities.Position;

/**
 * Interface representing a generic entity in the game.
 * <p>
 * All game entities (e.g., plants, zombies, bullets) must implement this interface.
 * Provides methods for managing position, collision, and periodic updates.
 */
public interface Entity {

    /**
     * Sets the position of the entity.
     *
     * @param position the new position; must not be {@code null}.
     */
    void setPosition(Position position);

    /**
     * Retrieves the current position of the entity.
     *
     * @return the current {@link Position}.
     */
    Position getPosition();

    /**
     * Updates the state of the entity based on the elapsed time.
     *
     * @param deltaTime the time since the last update in milliseconds.
     * @param entitiesManager the manager responsible for all entities in the game.
     */
    void update(long deltaTime, EntitiesManager entitiesManager);

    /**
     * Gets the hitbox used for collision detection.
     *
     * @return the {@link HitBox} of this entity.
     */
    HitBox getHitBox();
}
