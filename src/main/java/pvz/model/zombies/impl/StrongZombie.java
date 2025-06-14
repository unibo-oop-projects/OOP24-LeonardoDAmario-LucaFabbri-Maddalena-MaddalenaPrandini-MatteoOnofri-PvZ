package pvz.model.zombies.impl;

import pvz.utilities.*;

public class StrongZombie extends AbstractZombie {

    private static final int STRONG_ZOMBIE_HEALTH = 150;
    private static final int STRONG_ZOMBIE_SPEED = 1;
    private static final int STRONG_ZOMBIE_DAMAGE = 45;

    public StrongZombie(Position position) {
        super(position, STRONG_ZOMBIE_HEALTH, STRONG_ZOMBIE_SPEED);
    }
 
    @Override
    public int getDamage() {
        return STRONG_ZOMBIE_DAMAGE;
    }
    
}
