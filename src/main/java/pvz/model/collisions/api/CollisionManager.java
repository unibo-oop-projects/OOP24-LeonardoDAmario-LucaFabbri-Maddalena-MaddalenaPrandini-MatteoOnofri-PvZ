package pvz.model.collisions.api;

import java.util.Optional;

import pvz.model.entities.api.EntitiesManager;
import pvz.model.entities.api.Entity;

public interface CollisionManager {
    public Optional<Entity> handleCollision(Entity entity, EntitiesManager entitiesManager);
}
