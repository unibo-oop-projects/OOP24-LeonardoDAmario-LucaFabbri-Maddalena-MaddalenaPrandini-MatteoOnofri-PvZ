package pvz.model.collisions.api;

import pvz.model.entities.api.EntitiesManager;
import pvz.model.entities.api.Entity;

import java.util.Optional;

public interface CollisionManager {
    public Optional<Entity> handleCollision(Entity entity, EntitiesManager entitiesManager);
}
