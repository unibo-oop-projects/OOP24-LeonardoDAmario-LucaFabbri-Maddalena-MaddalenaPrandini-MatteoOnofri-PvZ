package PvZ.model.impl.Plants;

import java.util.Objects;
import PvZ.model.api.BasePlant;
import PvZ.model.api.PlantActionStrategy;
import PvZ.model.api.PlantType;
import PvZ.model.impl.AbstractEntity;
import PvZ.utilities.Position;

public class Plant extends AbstractEntity implements BasePlant {

    private int life;
    private final PlantActionStrategy strategy;
    private boolean alive = true;
    private PlantType plantType;

    public Plant(final PlantActionStrategy strategy, final PlantType plantType, final Position position) {
        super(position);
        Objects.requireNonNull(strategy, "The strategy cannot be null");
        this.strategy = strategy;
        this.life = this.strategy.getInitialLife();
        this.plantType = plantType;
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

    @Override
    public PlantType getType() {
        return this.plantType;
    }

}
