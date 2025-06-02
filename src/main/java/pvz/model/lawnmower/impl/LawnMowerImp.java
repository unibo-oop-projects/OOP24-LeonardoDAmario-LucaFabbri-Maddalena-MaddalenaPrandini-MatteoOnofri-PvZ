package pvz.model.lawnmower.impl;

import pvz.model.collisions.api.CollisionManager;
import pvz.model.collisions.impl.CollisionManagerImpl;
import pvz.model.collisions.impl.HitBoxFactory;
import pvz.model.entities.api.EntitiesManager;
import pvz.model.entities.api.Entity;
import pvz.model.entities.impl.AbstractEntity;
import pvz.model.lawnmower.api.LawnMower;
import pvz.model.zombies.api.Zombie;
import pvz.utilities.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LawnMowerImp extends AbstractEntity implements LawnMower {
    /**
     * Constructs an entity with the given position and hitbox type.
     *
     * @param position   the initial position of the entity; must not be {@code null}.
     * @param hitBoxType the type of hitbox to associate with this entity.
     */
    private final int speed = 4;        // velocità di scorrimento
    private CollisionManager collisionManager;

    public LawnMowerImp(Position position, HitBoxFactory.HitBoxType hitBoxType) {
        super(position, hitBoxType);
        this.collisionManager = new CollisionManagerImpl();

    }

    private void move() {
        final double move = this.speed * (1 / 100.0);
        final double newX = this.getPosition().x() + move;
        this.setPosition(new Position(newX, this.getPosition().y()));
        this.getHitBox().update(this.getPosition());
    }

    @Override
    public void update(long deltaTime, EntitiesManager entitiesManager) {
        Optional<Zombie> zombie = this.collisionManager.handleCollision(this, entitiesManager).map(entity -> (Zombie) entity);
        if(zombie.isPresent()) {
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
        if(this.getPosition().x() > 9) {
            entitiesManager.removeEntity(this);
        }
    }
}