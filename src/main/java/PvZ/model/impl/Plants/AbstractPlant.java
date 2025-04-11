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

    public final void plantAction(){
        if(isAlive()){
            this.action();
        }
        else {
            System.out.println("The plant is dead and cannot perform any action.");
        }
    }

    protected abstract void action();

    @Override
    public void update(long deltaTime, EntitiesManager entitiesManager) {
        this.plantAction();
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
