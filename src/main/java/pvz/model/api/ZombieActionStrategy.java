package pvz.model.api;

import pvz.model.impl.zombies.ZombieImpl;

public interface ZombieActionStrategy {

   void zombieAction(ZombieImpl zombie);

   void takeDamage(ZombieImpl zombie, int damage);
}
