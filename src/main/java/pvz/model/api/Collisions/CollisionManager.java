package pvz.model.api.Collisions;

import java.util.Optional;

import pvz.model.api.Entities.EntitiesManager;
import pvz.model.api.Entities.Entity;

public interface CollisionManager {
    public Optional<Entity> handleCollision(Entity entity, EntitiesManager entitiesManager);
}
