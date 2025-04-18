package PvZ.model.impl.Entitities;

import PvZ.model.api.Entities.Entity;
import PvZ.utilities.Position;

public abstract class AbstractEntity implements Entity {

    private Position position;

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
}
