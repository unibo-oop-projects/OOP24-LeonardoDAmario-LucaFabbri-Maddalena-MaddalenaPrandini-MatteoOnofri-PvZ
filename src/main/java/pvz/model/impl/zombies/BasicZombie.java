package pvz.model.impl.zombies;

import pvz.model.api.ZombieActionStrategy;
import pvz.utilities.Position;

public class BasicZombie {

    public static ZombieImpl create(ZombieActionStrategy strategy) {
        return new ZombieImpl(new Position(0, 0), 100, 1, strategy);
    }
}