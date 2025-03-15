package PvZ.model.impl;

import PvZ.model.api.BasePlant;

public class WallNut extends AbstractEntity implements BasePlant{
    private final int CONST=200;
    private int life=CONST;
    private boolean alive=true;

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
