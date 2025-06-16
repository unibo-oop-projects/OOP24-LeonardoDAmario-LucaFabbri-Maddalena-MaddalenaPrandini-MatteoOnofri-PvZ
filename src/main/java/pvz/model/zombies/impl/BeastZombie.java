package pvz.model.zombies.impl;

import pvz.model.zombies.api.ZombieType;
import pvz.utilities.*;

public class BeastZombie extends AbstractZombie {

    private static final int BEAST_ZOMBIE_HEALTH = 250;
    private static final int BEAST_ZOMBIE_SPEED = 1;
    private static final int BEAST_ZOMBIE_DAMAGE = 150;

    public BeastZombie(Position position) {
        super(position, BEAST_ZOMBIE_HEALTH, BEAST_ZOMBIE_SPEED);
    }
 
    @Override
    public int getDamage() {
        return BEAST_ZOMBIE_DAMAGE;
    }

    @Override
    public ZombieType getType() {
        return ZombieType.BEASTZOMBIE;
    }
    
}
