package PvZ.model.api.Collisions;

import PvZ.utilities.Position;

public interface HitBox {
    
    boolean isColliding(final HitBox hitbox);
    void update(final Position pos);    
    double getX();
    double getY();
    double getHeight();
    double getWidth();
}
