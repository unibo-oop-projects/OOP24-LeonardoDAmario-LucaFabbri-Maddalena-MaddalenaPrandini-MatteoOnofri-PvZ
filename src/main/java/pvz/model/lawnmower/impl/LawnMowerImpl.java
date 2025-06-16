package pvz.model.lawnmower.impl;

import pvz.model.collisions.api.CollisionManager;
import pvz.model.collisions.impl.CollisionManagerImpl;
import pvz.model.collisions.impl.HitBoxFactory;
import pvz.model.game.api.EntitiesManager;
import pvz.model.entities.api.Entity;
import pvz.model.entities.impl.AbstractEntity;
import pvz.model.lawnmower.api.LawnMower;
import pvz.model.zombies.api.Zombie;
import pvz.utilities.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents the implementation of a Lawn Mower entity.
 * It moves horizontally and kills any zombie it collides with.
 */
public class LawnMowerImp extends AbstractEntity implements LawnMower {

    /**
     * The movement speed of the lawn mower.
     */
    private final int speed = 4;

    private CollisionManager collisionManager;

    /**
     * Constructs a new LawnMowerImp with the given position and hitbox type.
     *
     * @param position   the initial position of the mower; must not be {@code null}.
     * @param hitBoxType the type of hitbox to assign to this mower.
     */
    public LawnMowerImp(final Position position, final HitBoxFactory.HitBoxType hitBoxType) {
        super(position, hitBoxType);
        this.collisionManager = new CollisionManagerImpl();
    }

    /**
     * Updates the mower's state: moves it and handles collisions with zombies.
     *
     * @param deltaTime       the time elapsed since the last update (in ms).
     * @param entitiesManager the manager that contains all game entities.
     */
    @Override
    public void update(final long deltaTime, final EntitiesManager entitiesManager) {
        Optional<Zombie> zombie = this.collisionManager.handleCollision(this, entitiesManager)
                .map(entity -> (Zombie) entity);
        if (zombie.isPresent()) {
            zombie.get().forceKill();
            if (!zombie.get().isAlive()) {
                entitiesManager.addKill();
                entitiesManager.removeEntity(zombie.get());
            }
        }
        this.move();
        List<Entity> snapshot = new ArrayList<>(entitiesManager.getEntities());
        for (Entity e : snapshot) {
            if (e instanceof Zombie z
                    && z.getPosition().y() == this.getPosition().y()
                    && this.getHitBox().isColliding(z.getHitBox())) {

                z.forceKill();
                if (!z.isAlive()) {
                    entitiesManager.addKill();
                    entitiesManager.removeEntity(z);
                }
            }
        }
    }

    /**
     * Moves the lawn mower forward based on its speed and updates its hitbox.
     */
    private void move() {
        final double move = this.speed * (1 / 100.0);
        final double newX = this.getPosition().x() + move;
        this.setPosition(new Position(newX, this.getPosition().y()));
        this.getHitBox().update(this.getPosition());
    }
}
