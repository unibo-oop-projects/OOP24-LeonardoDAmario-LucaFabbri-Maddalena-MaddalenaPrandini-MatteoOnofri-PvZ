package PvZ.model.impl;

import PvZ.model.api.Entity;
import PvZ.utilities.HitBox;
import PvZ.utilities.Position;

public abstract class AbstractEntity implements Entity {

    private Position position;
    private HitBox hitBox;

    public AbstractEntity(Position position) {
        this.position = position;
    }

    @Override
    public void setPosition(Position position) {
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
