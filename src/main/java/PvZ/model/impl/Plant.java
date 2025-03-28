package PvZ.model.impl;

import java.util.Objects;
import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantActionStrategy;

public class Plant extends AbstractEntity implements BasePlant {
    private int life;
    private final PlantActionStrategy strategy;
    private boolean alive = true;

    public Plant(final PlantActionStrategy strategy) {
        Objects.requireNonNull(strategy, "The strategy cannot be null");
        this.strategy = strategy;
        this.life = this.strategy.getInitialLife();
    }

    @Override
    public void update() {
        if (this.life <= 0) {
            this.alive = false;
        }
        strategy.plantAction(this);
    }

    @Override
    public int getLife() {
        return this.life;
    }

    @Override
    public void decreaseLife(final int damage) {
        this.life -= damage;
    }

    @Override
    public void plantAction() {
        this.strategy.plantAction(this);
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

}
