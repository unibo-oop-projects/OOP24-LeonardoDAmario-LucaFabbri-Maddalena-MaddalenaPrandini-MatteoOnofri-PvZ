package PvZ.model.api.Collisions;

import java.util.Optional;

import PvZ.model.api.Entities.EntitiesManager;
import PvZ.model.api.Entities.Entity;

public interface CollisionManager {
    public Optional<Entity> handleCollision(Entity entity, EntitiesManager entitiesManager);
}
