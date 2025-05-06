package PvZ.model.api.Collisions;

import PvZ.model.api.Entities.EntitiesManager;
import PvZ.model.api.Entities.Entity;

public interface CollisionManager {
    public void handleCollision(Entity entity, EntitiesManager entitiesManager);
}
