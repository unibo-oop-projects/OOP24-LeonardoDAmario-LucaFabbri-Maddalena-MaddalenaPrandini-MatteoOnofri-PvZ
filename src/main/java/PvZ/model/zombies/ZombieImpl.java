package PvZ.model.zombies;

import PvZ.model.api.Entity;
import PvZ.model.api.ZombieActionStrategy;
import PvZ.utilities.Position;
import PvZ.model.api.EntitiesManager;

public class ZombieImpl implements Entity {
    private int health;
    private int speed;
    private boolean alive;
    private ZombieActionStrategy strategy;
    private Position position;

    public ZombieImpl(int health, int speed, ZombieActionStrategy strategy) {
        this.health = health;
        this.speed = speed;
        this.strategy = strategy;
        this.alive=true;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void update(long deltaTime, EntitiesManager entitiesManager) {
        if (strategy != null) {
            strategy.zombieAction(this);
        }
        move();
    }

    public void move() {
        System.out.println("Zombie is moving at speed: " + speed);
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0; 
            alive=false;
        }
    }

    public boolean isAlive() {
        return alive && health > 0;
    }

    public void forceKill(){
        alive = false;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }
}