package PvZ.model.api;

import PvZ.model.impl.zombies.ZombieImpl;

public interface ZombieActionStrategy {

   void zombieAction(ZombieImpl zombie);

   void takeDamage(ZombieImpl zombie, int damage);
}
