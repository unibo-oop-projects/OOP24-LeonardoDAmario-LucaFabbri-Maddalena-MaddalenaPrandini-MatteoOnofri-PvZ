package PvZ.model.api.Entities;

import java.util.Set;

public interface EntitiesManager {
    void addEntity(Entity entity);
    void removeEntity(Entity entity);
    Set<Entity> getEntities();
    public void addSun(int amount);
    public boolean spendSun(int amount);
    public void addKill();
    int getKillCount();
    int getSunCount();
}
