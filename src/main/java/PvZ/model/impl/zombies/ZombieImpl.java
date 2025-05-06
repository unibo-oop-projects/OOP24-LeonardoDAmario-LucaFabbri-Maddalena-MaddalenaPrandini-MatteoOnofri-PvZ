package PvZ.model.impl.zombies;

//import PvZ.model.api.Entities.Entity;
import PvZ.model.impl.Collisions.HitBoxFactory.HitBoxType;
import PvZ.model.impl.Entitities.AbstractEntity;
import PvZ.model.api.Zombie;
import PvZ.model.api.ZombieActionStrategy;
import PvZ.model.api.Collisions.HitBox;
import PvZ.utilities.Position;
import PvZ.model.api.Entities.EntitiesManager;

public class ZombieImpl extends AbstractEntity implements Zombie {

    private int health;
    private int speed;
    private boolean alive;
    private ZombieActionStrategy strategy;
    //private Position position;

    public ZombieImpl(final Position position, final int health, final int speed, final ZombieActionStrategy strategy) {
        super(position, HitBoxType.ZOMBIE);
        this.health = health;
        this.speed = speed;
        this.strategy = strategy;
        this.alive = true;
    }


    @Override
    public void update(long deltaTime, EntitiesManager entitiesManager) {
        if (strategy != null) strategy.zombieAction(this);
        move();
    }

    @Override
    public void move() {
        System.out.println("Zombie is moving at speed: " + speed);
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