package PvZ.model.api;

public interface BasePlant extends Entity {
    //plants fetures
    int getLife();
    void setLife(int life);
    void decreaseLife(int life);

    //action plants
    void plantAction();
}
