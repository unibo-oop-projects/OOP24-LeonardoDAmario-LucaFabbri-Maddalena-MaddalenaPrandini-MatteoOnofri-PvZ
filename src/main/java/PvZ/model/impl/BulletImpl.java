package PvZ.model.impl;

import java.util.Set;
import java.util.stream.Collectors;

import PvZ.model.api.Bullet;
import PvZ.model.api.EntitiesManager;
import PvZ.model.api.Zombie;
import PvZ.utilities.HitBox;
import PvZ.utilities.HitBoxImpl;
import PvZ.utilities.Position; 

public class BulletImpl extends AbstractEntity implements Bullet {

    private Position pos;
    private HitBox hitBox;
    private long elapsedTime = 0;

    private static final double SPEED = 0.1;
    private static final int DAMAGE = 25;
    private static final long UPDATE_RATE = 500;

    public BulletImpl(final Position pos) {
        super(pos);
        this.hitBox = new HitBoxImpl(pos);
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
            Set<Zombie> zombieSet = entitiesManager.getEntities().stream()
                    .filter(entity -> entity instanceof Zombie)
                    .map(entity -> (Zombie) entity)
                    .collect(Collectors.toSet());
            for (Zombie zombie : zombieSet) {
                if(this.hitBox.isColliding(zombie.getHitBox())) {
                    zombie.decreaseLife(DAMAGE);
                    if(!zombie.isAlive()) {
                        entitiesManager.removeEntity(zombie);
                        entitiesManager.addKill();
                    }
                    entitiesManager.removeEntity(this);
                };
            }
        }
    }
    
    private Position move(final long time) {
        return new Position(this.getPosition().x() + time * SPEED, this.getPosition().y());
    }
}
