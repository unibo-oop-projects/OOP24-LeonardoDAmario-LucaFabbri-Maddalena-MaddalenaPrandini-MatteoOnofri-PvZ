package PvZ.model.impl;

import java.util.HashSet;
import java.util.Set;

import PvZ.model.api.EntitiesManager;
import PvZ.model.api.Entity;

public class EntitiesManagerImpl implements EntitiesManager{
    Set<Entity> entities = new HashSet<>();
    private static final int DEFAULTSUNS = 50;
    private static final int DEFAULTKILLS = 0;
    private int sunCount;
    private int killCount;

    public EntitiesManagerImpl() {
        this.sunCount = DEFAULTSUNS;
        this.killCount = DEFAULTKILLS;
    }

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

    @Override
    public void addSun(int amount) {
        this.sunCount += amount;
    }

    @Override
    public boolean spendSun(int amount) {
        if (sunCount >= amount) {
            sunCount -= amount;
            return true;
        }
        return false;
    }

    @Override
    public void addKill() {
        this.killCount += 1;
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
