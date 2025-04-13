package PvZ.model.impl;

import PvZ.model.api.Bullet;
import PvZ.model.api.EntitiesManager;
import PvZ.utilities.CollisionManager;
import PvZ.utilities.CollisionManagerImpl;
import PvZ.utilities.HitBox;
import PvZ.utilities.HitBoxImpl;
import PvZ.utilities.Position; 

public class BulletImpl extends AbstractEntity implements Bullet {

    private Position pos;
    private HitBox hitBox;
    private long elapsedTime = 0;
    private CollisionManager collisionManager;

    private static final double SPEED = 0.1;
    private static final int DAMAGE = 25;
    private static final long UPDATE_RATE = 500;

    public BulletImpl(final Position pos) {
        super(pos);
        this.hitBox = new HitBoxImpl(pos);
        this.collisionManager = new CollisionManagerImpl();
    }

    private boolean canUpdate(final long deltaTime) {
        this.elapsedTime = this.elapsedTime + deltaTime;
        if(this.elapsedTime >= UPDATE_RATE) {
            this.elapsedTime = 0;
            return true;
        }
        return false;
    }

    @Override
    public void update(long deltaTime, EntitiesManager entitiesManager) {
        if(this.canUpdate(deltaTime)) {            
            this.setPosition(this.move(deltaTime));
            this.hitBox.update(pos);
            this.collisionManager.handleCollision(this, entitiesManager);
        }
    }
    
    private Position move(final long time) {
        return new Position(this.getPosition().x() + time * SPEED, this.getPosition().y());
    }

    @Override
    public int getDamage() {
        return DAMAGE;
    }
}
