package PvZ.model.api;

import PvZ.utilities.Position;

public interface Zombie extends Entity {

    int getHealth();

    int getSpeed();

    void takeDamage(int damage);

    boolean isAlive();

    void setPosition(Position position);

    Position getPosition();

    void move();
}
