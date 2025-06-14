package pvz.model.zombies.impl;

import pvz.utilities.*;

public class BasicZombie extends AbstractZombie {

    private static final int BASIC_ZOMBIE_HEALTH = 100;
    private static final int BASIC_ZOMBIE_SPEED = 1;
    private static final int BASIC_ZOMBIE_DAMAGE = 35;

    public BasicZombie(Position position) {
        super(position, BASIC_ZOMBIE_HEALTH, BASIC_ZOMBIE_SPEED);
    }
 
    @Override
    public int getDamage() {
        return BASIC_ZOMBIE_DAMAGE;
    }
    
}
