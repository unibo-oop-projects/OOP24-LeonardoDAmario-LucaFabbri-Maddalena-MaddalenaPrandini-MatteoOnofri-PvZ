package PvZ.model.zombies;

import PvZ.model.api.Entity;
import PvZ.model.api.ZombieActionStrategy;
import PvZ.utilities.Position;

public class Zombie implements Entity {
    private int health;
    private int speed;
    private ZombieActionStrategy strategy;
    private Position position;

    public Zombie(int health, int speed, ZombieActionStrategy strategy) {
        this.health = health;
        this.speed = speed;
        this.strategy = strategy;
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
    public void update() {
        if (strategy != null) {
            strategy.zombieAction(this);
        }
    }

    public void move() {
        System.out.println("Zombie is moving at speed: " + speed);
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0; 
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }
}