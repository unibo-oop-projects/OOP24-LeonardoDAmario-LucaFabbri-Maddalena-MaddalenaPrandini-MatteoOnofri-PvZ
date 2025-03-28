package PvZ.model.impl;

import PvZ.model.api.Entity;
import PvZ.utilities.Position;

public abstract class AbstractEntity implements Entity{
    private Position position;

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
