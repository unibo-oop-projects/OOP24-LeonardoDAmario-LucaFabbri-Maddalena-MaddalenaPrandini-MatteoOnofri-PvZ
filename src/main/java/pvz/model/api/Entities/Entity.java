package pvz.model.api.Entities;

import pvz.model.api.Collisions.HitBox;
import pvz.utilities.*;

public interface Entity {
    //setting delle posizioni uguali per tutte le entità
    void setPosition(Position Position);
    Position getPosition();

    //updating plants
    void update(long deltaTime, EntitiesManager entitiesManager);
    HitBox getHitBox();
}
