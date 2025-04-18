package PvZ.model.impl.Plants;

import PvZ.model.api.Entities.EntitiesManager;
import PvZ.model.api.Plants.Plant;
import PvZ.model.impl.Collisions.HitBoxFactory.HitBoxType;
import PvZ.model.impl.Entitities.AbstractEntity;
import PvZ.utilities.Position;

public abstract class AbstractPlant extends AbstractEntity implements Plant{
    private int damage;

    public AbstractPlant(Position position) {
        super(position, HitBoxType.PLANT);
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
