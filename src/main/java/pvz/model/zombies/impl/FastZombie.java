package pvz.model.zombies.impl;

import pvz.model.zombies.api.ZombieType;
import pvz.utilities.*;

public class FastZombie extends AbstractZombie {

    private static final int FAST_ZOMBIE_HEALTH = 80;
    private static final int FAST_ZOMBIE_SPEED = 2;
    private static final int FAST_ZOMBIE_DAMAGE = 35;

    public FastZombie(Position position) {
        super(position, FAST_ZOMBIE_HEALTH, FAST_ZOMBIE_SPEED);
    }
 
    @Override
    public int getDamage() {
        return FAST_ZOMBIE_DAMAGE;
    }

    @Override
    public ZombieType getType() {
        return ZombieType.FASTZOMBIE;
    }
    
}
