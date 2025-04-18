package PvZ.model.api;

import PvZ.model.api.Entities.Entity;

public interface Zombie extends Entity{

    void decreaseLife(final int damage);
    boolean isAlive();
    int getDamage();

}
