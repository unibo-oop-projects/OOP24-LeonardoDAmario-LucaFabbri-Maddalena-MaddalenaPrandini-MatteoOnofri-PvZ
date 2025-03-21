package PvZ.model.impl;

import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantActionStrategy;

public class Plant extends AbstractEntity implements BasePlant{
    private int life;
    private PlantActionStrategy actionStrategy;
    private boolean alive=true;

    public Plant(PlantActionStrategy action) {
        this.life = actionStrategy.getInitialLife();
        this.actionStrategy = action;
    }

    @Override
    public void update() {
        if(this.life<=0) {
            this.alive=false;
        }
        actionStrategy.PlantAction(this);
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
        actionStrategy.PlantAction(this);
    }

}
