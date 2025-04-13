package PvZ.model.impl.Plants;

import PvZ.model.api.EntitiesManager;
import PvZ.model.api.Plant;
import PvZ.model.api.PlantType;
import PvZ.utilities.Position;

public abstract class AbstractPlant implements Plant{
    protected int life;
    protected PlantType type;
    protected Position position;

    public AbstractPlant(PlantType type) {
        this.type = type;
        this.life = type.getLife();
    }

    protected abstract void action(long deltaTime, EntitiesManager entitiesManager);

    @Override
    public void update(long deltaTime, EntitiesManager entitiesManager) {
        this.action(deltaTime, entitiesManager);
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
    public boolean isAlive() {
        return this.life > 0;
    }

    @Override
    public PlantType getType() {
        return this.type;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }
    
}
