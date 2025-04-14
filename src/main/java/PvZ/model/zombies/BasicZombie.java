package PvZ.model.zombies;

import PvZ.model.api.ZombieActionStrategy;

public class BasicZombie {

    public static ZombieImpl create(ZombieActionStrategy strategy) {
        return new ZombieImpl(100, 1, strategy);
    }
}