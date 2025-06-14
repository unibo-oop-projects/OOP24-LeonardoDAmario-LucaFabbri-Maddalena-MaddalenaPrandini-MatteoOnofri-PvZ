package pvz.model.zombies.impl;

import pvz.utilities.*;

public class BeastZombie extends AbstractZombie {

    private static final int BEAST_ZOMBIE_HEALTH = 200;
    private static final int BEAST_ZOMBIE_SPEED = 1;
    private static final int BEAST_ZOMBIE_DAMAGE = 50;

    public BeastZombie(Position position) {
        super(position, BEAST_ZOMBIE_HEALTH, BEAST_ZOMBIE_SPEED);
    }
 
    @Override
    public int getDamage() {
        return BEAST_ZOMBIE_DAMAGE;
    }
    
}
