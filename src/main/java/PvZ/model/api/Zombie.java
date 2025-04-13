package PvZ.model.api;

import PvZ.utilities.HitBox;

public interface Zombie extends Entity{

    HitBox getHitBox();
    void decreaseLife(final int damage);
    boolean isAlive();
    
}
