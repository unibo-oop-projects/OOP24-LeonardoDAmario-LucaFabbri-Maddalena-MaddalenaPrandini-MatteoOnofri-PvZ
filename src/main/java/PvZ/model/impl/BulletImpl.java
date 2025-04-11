package PvZ.model.impl;

import PvZ.model.api.Bullet;
import PvZ.model.api.EntitiesManager;
import PvZ.utilities.Position; 

public class BulletImpl extends AbstractEntity implements Bullet {

    private final int DAMAGE=25;
    private Position pos;
    private boolean alive;

    public BulletImpl(final Position pos) {
        super(pos);
        this.alive = true;
    }

    public int getDamage() {
        return this.DAMAGE;
    }

    public void die() {
        this.alive = false;
    }

    @Override
    public void update(long deltaTime, EntitiesManager entitiesManager) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
