package pvz.model.entities.impl;

import pvz.model.collisions.api.HitBox;
import pvz.model.entities.api.Entity;
import pvz.model.collisions.impl.HitBoxFactory;
import pvz.model.collisions.impl.HitBoxFactory.HitBoxType;
import pvz.utilities.Position;

/**
 * Abstract base class for all entities in the game.
 * <p>
 * Provides common functionality for position and hitbox management.
 * All specific entities (e.g., plants, zombies, bullets) should extend this class.
 */
public abstract class AbstractEntity implements Entity {

    private Position position;
    private final HitBox hitBox;

    /**
     * Constructs an entity with the given position and hitbox type.
     *
     * @param position the initial position of the entity; must not be {@code null}.
     * @param hitBoxType the type of hitbox to associate with this entity.
     */
    public AbstractEntity(final Position position, final HitBoxType hitBoxType) {
        this.position = position;
        this.hitBox = HitBoxFactory.createHitBox(position, hitBoxType);
    }

    /**
     * Sets the new position of the entity.
     *
     * @param position the new position to set; must not be {@code null}.
     */
    @Override
    public void setPosition(final Position position) {
        this.position = position;
    }

    /**
     * Gets the current position of the entity.
     *
     * @return the current {@link Position} of the entity.
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * Gets the {@link HitBox} of the entity.
     *
     * @return the hitbox associated with the entity.
     */
    @Override
    public HitBox getHitBox() {
        return hitBox;
    }
}
