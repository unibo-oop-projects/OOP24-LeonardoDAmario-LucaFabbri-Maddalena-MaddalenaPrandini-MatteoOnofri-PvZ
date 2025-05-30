package pvz.model.zombies.impl;

import pvz.model.collisions.impl.CollisionManagerImpl;
import pvz.model.collisions.impl.HitBoxFactory.HitBoxType;
import pvz.model.entities.impl.AbstractEntity;

import java.util.Optional;

import pvz.model.zombies.api.Zombie;
import pvz.model.collisions.api.CollisionManager;
import pvz.model.collisions.api.HitBox;
import pvz.utilities.Position;
import pvz.model.entities.api.EntitiesManager;
import pvz.model.plants.api.Plant;

public class ZombieImpl extends AbstractEntity implements Zombie {

    private int health;
    private int speed;
    private boolean alive;
    private CollisionManager collisionManager;
    private static final long ATTACK_RATE = 2000;
    private long lastAttackTime = 0;

    public ZombieImpl(final Position position, final int health, final int speed) {
        super(position, HitBoxType.ZOMBIE);
        this.health = health;
        this.speed = speed;
        this.alive = true;
        this.collisionManager = new CollisionManagerImpl();
        this.lastAttackTime = ATTACK_RATE;
    }

    @Override
    public void update(long deltaTime, EntitiesManager entitiesManager) {

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
        return 35; 
    }
}