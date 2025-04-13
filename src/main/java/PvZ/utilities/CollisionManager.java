package PvZ.utilities;

import PvZ.model.api.EntitiesManager;
import PvZ.model.api.Entity;

public interface CollisionManager {
    public void handleCollision(Entity entity, EntitiesManager entitiesManager);
}
