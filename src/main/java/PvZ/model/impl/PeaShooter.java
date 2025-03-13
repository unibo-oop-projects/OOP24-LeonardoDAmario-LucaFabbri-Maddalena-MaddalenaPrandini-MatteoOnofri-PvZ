package PvZ.model.impl;

import PvZ.model.api.BasePlant;

public class PeaShooter extends AbstractEntity implements BasePlant {
    private final int PRICE=100;
    private final int CONST=100;
    private int life=CONST;
    private boolean alive=true;

    @Override
    public int getPrice() { //da spostare eventualmente nel roaster
        return PRICE;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void decreaseLife(int damage) {
        this.life -= damage;
    }

    @Override
    public void plantAction() {
        /*chiamare metodo sparasemi proiettili dams passando come parametro la mia posizione*/
    }

    @Override
    public void update() {
        /*verificare se la pianta può sparare */
        if(life<=0){
            alive=false;
        }
        this.plantAction();
    }

}
