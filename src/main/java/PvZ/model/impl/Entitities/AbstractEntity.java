package PvZ.model.impl.Entitities;

import PvZ.model.api.Collisions.HitBox;
import PvZ.model.api.Entities.Entity;
import PvZ.model.impl.Collisions.HitBoxFactory;
import PvZ.model.impl.Collisions.HitBoxFactory.HitBoxType;
import PvZ.utilities.Position;

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
