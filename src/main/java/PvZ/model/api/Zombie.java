package PvZ.model.api;

import PvZ.model.api.Entities.Entity;

import PvZ.utilities.Position;

public interface Zombie extends Entity {

    void decreaseLife(final int damage);
   
    int getDamage();

    int getHealth();

    int getSpeed();

    void takeDamage(int damage);

    boolean isAlive();

    void setPosition(Position position);

    Position getPosition();

    void move();

    void forceKill();
}
