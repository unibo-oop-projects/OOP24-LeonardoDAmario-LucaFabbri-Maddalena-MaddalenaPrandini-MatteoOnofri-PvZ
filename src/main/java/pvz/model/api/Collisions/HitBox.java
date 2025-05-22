package pvz.model.api.Collisions;

import pvz.utilities.Position;

public interface HitBox {
    
    boolean isColliding(final HitBox hitbox);
    void update(final Position pos);    
    double getX();
    double getWidth();
    
}
