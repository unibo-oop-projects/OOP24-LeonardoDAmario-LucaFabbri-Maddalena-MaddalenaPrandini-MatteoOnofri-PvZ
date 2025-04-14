package PvZ.model.api;

import PvZ.model.zombies.Zombie;

public interface ZombieActionStrategy {

    void zombieAction(Zombie zombie);

    void takeDamage(Zombie zombie, int damage);
}
