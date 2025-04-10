package PvZ.model.api;

import java.util.Set;

public interface EntitiesManager {
    void addEntity(Entity entity);
    void removeEntity(Entity entity);
    Set<Entity> getEntities();
}
