package PvZ.model.api;

public interface Zombie extends Entity{

    void decreaseLife(final int damage);
    boolean isAlive();
    int getDamage();

}
