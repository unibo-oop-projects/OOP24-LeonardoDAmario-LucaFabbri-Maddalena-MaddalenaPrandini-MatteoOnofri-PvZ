package PvZ.model.impl.Plants;

import PvZ.model.api.EntitiesManager;
import PvZ.model.api.Plant;
import PvZ.model.impl.AbstractEntity;
import PvZ.utilities.Position;

public abstract class AbstractPlant extends AbstractEntity implements Plant{
    private int damage;

    public AbstractPlant(Position position) {
        super(position);
    }

    @Override
    public abstract void update(long deltaTime, EntitiesManager entitiesManager);

    @Override
    public final int getLife(){
        return this.getMaxLife() - this.damage;
    }

    @Override
    public final void decreaseLife(int damage){
        this.damage= this.damage + damage;
    }

    protected abstract int getMaxLife();
    
}
