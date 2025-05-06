package PvZ.model.impl.Entitities;

import java.util.HashSet;
import java.util.Set;

import PvZ.model.api.Entities.EntitiesManager;
import PvZ.model.api.Entities.Entity;

public class EntitiesManagerImpl implements EntitiesManager{
    
    private static final int DEFAULT_SUNS = 50;
    private static final int DEFAULT_KILLS = 0;

    Set<Entity> entities = new HashSet<>();
    private int sunCount;
    private int killCount;

    public EntitiesManagerImpl() {
        this.sunCount = DEFAULT_SUNS;
        this.killCount = DEFAULT_KILLS;
    }

    @Override
    public void addEntity(Entity entity) {
        System.out.println("[ENTITY MANAGER] Added: " + entity.getClass().getSimpleName());
        this.entities.add(entity);
    }

    @Override
    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    @Override
    public Set<Entity> getEntities() {
        System.out.println("[ENTITY MANAGER] Returning: " + entities.size() + " entities");
        return Set.copyOf(this.entities);
    }

    @Override
    public void addSun(int amount) {
        this.sunCount = this.sunCount + amount;
    }

    @Override
    public boolean spendSun(int amount) {
        if (this.sunCount >= amount) {
            this.sunCount = this.sunCount-amount;
            return true;
        }
        return false;
    }

    @Override
    public void addKill() {
        this.killCount = this.killCount + 1;
    }

    @Override
    public int getKillCount() {
        return killCount;
    }

    @Override
    public int getSunCount() {
        return sunCount;
    }

}
