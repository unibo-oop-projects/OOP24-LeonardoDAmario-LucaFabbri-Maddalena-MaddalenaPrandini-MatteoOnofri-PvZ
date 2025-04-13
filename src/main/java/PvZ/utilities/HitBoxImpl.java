package PvZ.utilities;

public class HitBoxImpl implements HitBox{

    private double x;
    private double y;

    public HitBoxImpl(final Position pos) {
        this.update(pos);
    }

    @Override
    public boolean isColliding(HitBox hitbox) {
        return Math.abs(this.x - hitbox.getX()) < 1 && Math.abs(this.y - hitbox.getY()) < 1;
    }

    @Override
    public void update(Position pos) {
        this.x = pos.x() + 2;
        this.y = pos.y() + 2;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    
}
