package pvz.model.impl.Bullets;

import java.math.BigDecimal;
import java.util.Optional;

import pvz.utilities.Position;
import pvz.model.api.Zombie;
import pvz.model.api.Bullets.Bullet;
import pvz.model.api.Collisions.CollisionManager;
import pvz.model.api.entities.EntitiesManager;
import pvz.model.impl.Collisions.CollisionManagerImpl;
import pvz.model.impl.Collisions.HitBoxFactory.HitBoxType;
import pvz.model.impl.Entitities.AbstractEntity;

public class BulletImpl extends AbstractEntity implements Bullet {

    private long elapsedTime = 0;
    private CollisionManager collisionManager;

    private static final double SPEED = 0.03;
    private static final int DAMAGE = 25;
    private static final long UPDATE_RATE = 300;

    public BulletImpl(final Position pos) {
        super(pos, HitBoxType.BULLET);
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
            this.getHitBox().update(this.getPosition());
            Optional<Zombie> zombie = this.collisionManager.handleCollision(this, entitiesManager).map(entity -> (Zombie) entity);
            if(zombie.isPresent()) {
                this.attackZombie(zombie.get(), entitiesManager);
                entitiesManager.removeEntity(this);
            }
        }   
    }

    private void attackZombie(final Zombie zombie, EntitiesManager entitiesManager) {
        zombie.takeDamage(this.getDamage());
        if(!zombie.isAlive()) {
            entitiesManager.removeEntity(zombie);
            entitiesManager.addKill();
        }
    }
    
    private Position move(final long time) {
        final BigDecimal move = BigDecimal.valueOf(SPEED).multiply(BigDecimal.valueOf(time));
        final BigDecimal newX = move.add(BigDecimal.valueOf(this.getPosition().x()));
        return new Position(newX.doubleValue(), this.getPosition().y());
    }

    @Override
    public int getDamage() {
        return DAMAGE;
    }
}
