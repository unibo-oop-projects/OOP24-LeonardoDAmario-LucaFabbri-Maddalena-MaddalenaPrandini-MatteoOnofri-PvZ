package PvZ.model.impl.Collisions;

import PvZ.model.api.Collisions.HitBox;
import PvZ.utilities.Position;

public final class HitBoxFactory {

    private HitBoxFactory() {}

    public enum HitBoxType {

        PLANT(1.0, 1.0),
        ZOMBIE(1.5, 1.0),
        BULLET(0.5, 0.5);

        private final double width;
        private final double height;

        HitBoxType(double width, double height) {
            this.width = width;
            this.height = height;
        }

        public double getWidth() {
            return width;
        }

        public double getHeight() {
            return height;
        }
    }

    public static HitBox createHitBox(final Position pos, final HitBoxType type) {
        return new HitBox() {

            private double centerX = pos.x();
            private double centerY = pos.y();
            private final double width = type.getWidth();
            private final double height = type.getHeight();

            @Override
            public boolean isColliding(final HitBox hitbox) {
                return Math.abs(centerX - hitbox.getX()) < (width / 2 + hitbox.getWidth() / 2);
            }

            @Override
            public void update(final Position pos) {
                this.centerX = pos.x();
                this.centerY = pos.y();
            }

            @Override
            public double getX() {
                return centerX;
            }

            @Override
            public double getY() {
                return centerY;
            }

            @Override
            public double getWidth() {
                return width;
            }

            @Override
            public double getHeight() {
                return height;
            }
            
        };
    }
}
