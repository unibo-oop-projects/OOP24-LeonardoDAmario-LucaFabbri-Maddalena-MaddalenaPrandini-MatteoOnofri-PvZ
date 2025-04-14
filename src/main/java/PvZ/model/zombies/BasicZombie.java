package PvZ.model.zombies;

import PvZ.model.api.ZombieActionStrategy;

public class BasicZombie {

    public static Zombie create(ZombieActionStrategy strategy) {
        return new Zombie(100, 1, strategy);
    }
}