package PvZ.model.impl;

import PvZ.model.api.BasePlant;

public class Sunflower extends AbstractEntity implements BasePlant{
    private final int PRICE=50;
    private final int CONST=80;
    private int life=CONST;
    private boolean alive=true;

    @Override
    public int getPrice() {
        return this.PRICE;
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public void decreaseLife(int damage) {
       this.life -= damage;
    }

    @Override
    public void plantAction() {
        /*chiamare metodo generazione dei soli */
    }

    @Override
    public void update() {
        if(this.life<=0){
            this.alive=false;
        }
        this.plantAction();
    }

}
