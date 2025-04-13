package PvZ.utilities;

public interface HitBox {
    
    boolean isColliding(final HitBox hitbox);
    void update(final Position pos);    
    double getX();
    double getY();
}
