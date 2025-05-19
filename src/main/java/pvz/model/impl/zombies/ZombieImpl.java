package pvz.model.impl.zombies;

import pvz.model.impl.Collisions.CollisionManagerImpl;
import pvz.model.impl.Collisions.HitBoxFactory.HitBoxType;
import pvz.model.impl.Entitities.AbstractEntity;

import java.util.Optional;

import pvz.model.api.Zombie;
import pvz.model.api.ZombieActionStrategy;
import pvz.model.api.Collisions.CollisionManager;
import pvz.model.api.Collisions.HitBox;
import pvz.utilities.Position;
import pvz.model.api.entities.EntitiesManager;
import pvz.model.api.plants.Plant;

public class ZombieImpl extends AbstractEntity implements Zombie {

    private int health;
    private int speed;
    private boolean alive;
    private ZombieActionStrategy strategy;
    private CollisionManager collisionManager;
    private static final long ATTACK_RATE = 2000;
    private long lastAttackTime = 0;

    public ZombieImpl(final Position position, final int health, final int speed, final ZombieActionStrategy strategy) {
        super(position, HitBoxType.ZOMBIE);
        this.health = health;
        this.speed = speed;
        this.strategy = strategy;
        this.alive = true;
        this.collisionManager = new CollisionManagerImpl();
        this.lastAttackTime = ATTACK_RATE;
    }

    @Override
    public void update(long deltaTime, EntitiesManager entitiesManager) {
        if (strategy != null) {
            strategy.zombieAction(this);
            move(deltaTime);
        }
        Optional<Plant> plant = collisionManager.handleCollision(this, entitiesManager).map(entity -> (Plant) entity);
        if(plant.isPresent()) {
            lastAttackTime += deltaTime;
            if(lastAttackTime >= ATTACK_RATE) {
                plant.get().decreaseLife(this.getDamage());
                lastAttackTime = 0;
                if(plant.get().getLife() <= 0) {
                    lastAttackTime = ATTACK_RATE;
                    entitiesManager.removeEntity(plant.get());
                }
            }
        }
        else {
            this.move(deltaTime);
        }
    }

    
    public void move(long deltaTime) {
        final double move = this.speed * (1 / 100.0); 
        final double newX = this.getPosition().x() - move; 
        this.setPosition(new Position(newX, this.getPosition().y())); 
        this.getHitBox().update(this.getPosition()); 
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) alive = false;
    }

    @Override
    public boolean isAlive() {
        return alive && health > 0;
    }

    @Override
    public void forceKill() {
        alive = false;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public HitBox getHitBox() {
        return super.getHitBox();
    }


    @Override
    public void decreaseLife(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.alive = false;
        }
    }


    @Override
    public int getDamage() {
        return 20; //to be modified in strategy
    }
}