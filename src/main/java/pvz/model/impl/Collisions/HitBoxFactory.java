package pvz.model.impl.Collisions;

import pvz.model.api.Collisions.HitBox;
import pvz.utilities.Position;

public final class HitBoxFactory {

    private HitBoxFactory() {}

    public enum HitBoxType {

        PLANT(0.2),
        ZOMBIE(1.0),
        BULLET(0.5);

        private final double width;

        HitBoxType(double width) {
            this.width = width;
        }

        public double getWidth() {
            return width;
        }

    }

    public static HitBox createHitBox(final Position pos, final HitBoxType type) {
        return new HitBox() {

            private double centerX = pos.x();
            private final double width = type.getWidth();

            @Override
            public boolean isColliding(final HitBox hitbox) {
                return Math.abs(centerX - hitbox.getX()) < (width / 2 + hitbox.getWidth() / 2);
            }

            @Override
            public void update(final Position pos) {
                this.centerX = pos.x();
            }

            @Override
            public double getX() {
                return centerX;
            }

            @Override
            public double getWidth() {
                return width;
            }
            
        };
    }
}
