package PvZ.model.impl.zombies;

import PvZ.model.api.ZombieActionStrategy;
import PvZ.utilities.Position;

public class BasicZombie {

    public static ZombieImpl create(ZombieActionStrategy strategy) {
        return new ZombieImpl(new Position(0, 0), 100, 1, strategy);
    }
}