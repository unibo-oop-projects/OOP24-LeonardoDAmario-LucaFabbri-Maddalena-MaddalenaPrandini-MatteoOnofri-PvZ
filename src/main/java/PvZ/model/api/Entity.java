package PvZ.model.api;

import PvZ.utilities.*;

public interface Entity {
    //setting delle posizioni uguali per tutte le entità
    void setPosition(Position Position);
    Position getPosition();

    //updating plants
    protected void update();
}
