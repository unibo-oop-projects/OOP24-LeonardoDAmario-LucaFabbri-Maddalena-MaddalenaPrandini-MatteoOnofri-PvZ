package PvZ.model.impl.Collisions;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import PvZ.model.api.Zombie;
import PvZ.model.api.Bullets.Bullet;
import PvZ.model.api.Collisions.CollisionManager;
import PvZ.model.api.Entities.EntitiesManager;
import PvZ.model.api.Entities.Entity;
import PvZ.model.api.Plants.Plant;
import PvZ.model.api.Plants.PlantType;

public class CollisionManagerImpl implements CollisionManager{

    @Override
    public Optional<Entity> handleCollision(Entity entity, EntitiesManager entitiesManager) {
        if(entity instanceof Bullet) {
            return this.handleBulletZombieCollision((Bullet) entity,entitiesManager).map(zombie -> zombie);
        }
        else if(entity instanceof Zombie) {
            return this.handleZombiePlantCollision((Zombie) entity, entitiesManager).map(plant -> plant);
        }
        else if(entity instanceof Plant) {
            Plant plant = (Plant) entity;
            if(plant.mapToEntityType() == PlantType.WALLNUT) {
                return this.handleWallNutZombieCollision((Plant) entity, entitiesManager).map(zombie -> zombie);
            }
        }
        return Optional.empty();
    }

    private Optional<Zombie> handleWallNutZombieCollision(Plant wallNut, EntitiesManager entitiesManager) {
        Set<Zombie> zombieSet = entitiesManager.getEntities().stream()
            .filter(entity -> entity instanceof Zombie)
            .filter(zombie -> BigDecimal.valueOf(zombie.getPosition().y())
                   .compareTo(BigDecimal.valueOf(wallNut.getPosition().y())) == 0)
            .map(entity -> (Zombie) entity)
            .collect(Collectors.toSet());
        for (Zombie zombie : zombieSet) {
            if(wallNut.getHitBox().isColliding(zombie.getHitBox())) {
                return Optional.of(zombie);
            }
        };
        return Optional.empty();
    }

    private Optional<Zombie> handleBulletZombieCollision(Bullet bullet, EntitiesManager entitiesManager) {
        Set<Zombie> zombieSet = entitiesManager.getEntities().stream()
            .filter(entity -> entity instanceof Zombie)
            .filter(zombie -> BigDecimal.valueOf(zombie.getPosition().y())
                   .compareTo(BigDecimal.valueOf(bullet.getPosition().y())) == 0)
            .map(entity -> (Zombie) entity)
            .collect(Collectors.toSet());
        for (Zombie zombie : zombieSet) {
            if(bullet.getHitBox().isColliding(zombie.getHitBox())) {
                return Optional.of(zombie);
            }
        };
        return Optional.empty();
    }

    private Optional<Plant> handleZombiePlantCollision(Zombie zombie, EntitiesManager entitiesManager) {
        Set<Plant> plantSet = entitiesManager.getEntities().stream()
            .filter(entity -> entity instanceof Plant)
            .map(entity -> (Plant) entity)
            .filter(plant -> BigDecimal.valueOf(plant.getPosition().y())
                    .compareTo(BigDecimal.valueOf(zombie.getPosition().y())) == 0)
            .collect(Collectors.toSet());
        for (Plant plant : plantSet) {
            if(zombie.getHitBox().isColliding(plant.getHitBox())) {
                return Optional.of(plant);
            }
        }
        return Optional.empty();
    }
    
}
