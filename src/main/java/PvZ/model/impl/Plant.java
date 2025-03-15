package PvZ.model.impl;

import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantActionStrategy;

public class Plant extends AbstractEntity implements BasePlant{
    private int life;
    private PlantActionStrategy actionStrategy;
    private boolean alive=true;

    public Plant(PlantActionStrategy actionStrategy) {
        this.life = actionStrategy.getInitialLife();
        this.actionStrategy = actionStrategy;
    }

    @Override
    public void update() {
        if(this.life<=0) {
            this.alive=false;
        }
        this.plantAction();
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public void decreaseLife(int damage) {
        this.life-=damage;
    }

    @Override
    public void plantAction() {
        this.actionStrategy.PlantAction(this);
    }

}
