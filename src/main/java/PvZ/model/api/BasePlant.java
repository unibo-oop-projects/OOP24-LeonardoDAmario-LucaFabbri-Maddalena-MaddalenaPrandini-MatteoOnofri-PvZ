package PvZ.model.api;

public interface BasePlant extends Entity { //dato che la estende ha come metodi set e get position e update
    //plants fetures
    int getLife();
    /*
    void setLife(int life);*/
    void decreaseLife(int life);

    //action plants
    void plantAction();
}
