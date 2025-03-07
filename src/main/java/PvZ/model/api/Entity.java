package PvZ.model.api;

import PvZ.utilities.*;

public interface Entity {
    //setting delle posizioni
    void setPosition(Position Position);
    Position getPosition();

    //updating plants
    void update();
}
