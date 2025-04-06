package PvZ.model.impl;

import java.util.HashSet;
import java.util.Set;

import PvZ.model.api.EntitiesManager;
import PvZ.model.api.Entity;

public class EntitiesManagerImpl implements EntitiesManager{
    Set<Entity> entities = new HashSet<>();

    @Override
    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    @Override
    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    @Override
    public Set<Entity> getEntities() {
        return Set.copyOf(this.entities);
    }

}
