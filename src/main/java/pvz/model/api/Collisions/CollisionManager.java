package pvz.model.api.Collisions;

import java.util.Optional;

import pvz.model.api.entities.EntitiesManager;
import pvz.model.api.entities.Entity;

public interface CollisionManager {
    public Optional<Entity> handleCollision(Entity entity, EntitiesManager entitiesManager);
}
