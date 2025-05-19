package pvz.model.impl.Entitities;

import pvz.model.api.Collisions.HitBox;
import pvz.model.api.entities.Entity;
import pvz.model.impl.Collisions.HitBoxFactory;
import pvz.model.impl.Collisions.HitBoxFactory.HitBoxType;
import pvz.utilities.Position;

public abstract class AbstractEntity implements Entity {

    private Position position;
    final private HitBox hitBox;

    public AbstractEntity(final Position position, final HitBoxType hitBoxType) {
        this.position = position;
        this.hitBox = HitBoxFactory.createHitBox(position, hitBoxType);
    }

    @Override
    public void setPosition(final Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public HitBox getHitBox() {
        return hitBox;
    }
}
