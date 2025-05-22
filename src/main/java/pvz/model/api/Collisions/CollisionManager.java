package pvz.model.api.Collisions;

import java.util.Optional;

import pvz.model.api.entities.EntitiesManager;
import pvz.model.api.entities.Entity;

/**
 * Interface for managing collisions between entities in the game.
 * This interface defines a method to handle collisions between an entity and other entities in the game.
 */
public interface CollisionManager {
    /**
     * Checks for a collision between the specified entity and other entities managed by the given EntitiesManager.
     *
     * @param entity the entity for which to check collisions
     * @param entitiesManager the manager containing all entities in the game
     * @return an {@link Optional} containing the first entity that collides with the specified entity,
     *         or an empty {@link Optional} if no collision is detected
     */
    Optional<Entity> handleCollision(Entity entity, EntitiesManager entitiesManager);
}
