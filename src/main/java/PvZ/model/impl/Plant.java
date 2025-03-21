package PvZ.model.impl;

import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantActionStrategy;

public class Plant extends AbstractEntity implements BasePlant{
    private int life;
    private PlantActionStrategy strategy;
    private boolean alive=true;

    public Plant(PlantActionStrategy strategy) {
        if(strategy==null) {
            throw new IllegalArgumentException("Strategy cannot be null");
        }
        this.strategy=strategy;
        this.life=strategy.getInitialLife();
    }

    @Override
    public void update() {
        if(this.life<=0) {
            this.alive=false;
        }
        strategy.plantAction(this);
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
        strategy.plantAction(this);
    }

}
