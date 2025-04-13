package PvZ.utilities;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import PvZ.model.api.Bullet;
import PvZ.model.api.EntitiesManager;
import PvZ.model.api.Entity;
import PvZ.model.api.Plant;
import PvZ.model.api.Zombie;

public class CollisionManagerImpl implements CollisionManager{

    @Override
    public void handleCollision(Entity entity, EntitiesManager entitiesManager) {
        if(entity instanceof Bullet) {
            this.handleBulletZombieCollision((Bullet) entity,entitiesManager);
        }
        else if(entity instanceof Zombie) {
            this.handleZombiePlantCollision((Zombie) entity, entitiesManager);
        }
    }

    private void handleBulletZombieCollision(Bullet bullet, EntitiesManager entitiesManager) {
        Set<Zombie> zombieSet = entitiesManager.getEntities().stream()
            .filter(entity -> entity instanceof Zombie)
            .filter(zombie -> BigDecimal.valueOf(zombie.getPosition().y())
                   .compareTo(BigDecimal.valueOf(bullet.getPosition().y())) == 0)
            .map(entity -> (Zombie) entity)
            .collect(Collectors.toSet());
        for (Zombie zombie : zombieSet) {
            if(bullet.getHitBox().isColliding(zombie.getHitBox())) {
                zombie.decreaseLife(bullet.getDamage());
                if(!zombie.isAlive()) {
                    entitiesManager.removeEntity(zombie);
                    entitiesManager.addKill();
                }
                entitiesManager.removeEntity(bullet);
                return;
            }
        };
    }

    private void handleZombiePlantCollision(Zombie zombie, EntitiesManager entitiesManager) {
        Set<Plant> plantSet = entitiesManager.getEntities().stream()
            .filter(entity -> entity instanceof Plant)
            .map(entity -> (Plant) entity)
            .filter(plant -> BigDecimal.valueOf(plant.getPosition().y())
                    .compareTo(BigDecimal.valueOf(zombie.getPosition().y())) == 0)
            .collect(Collectors.toSet());
        for (Plant plant : plantSet) {
            if(zombie.getHitBox().isColliding(plant.getHitBox())) {
                plant.decreaseLife(zombie.getDamage());
                if(!plant.isAlive()) {
                    entitiesManager.removeEntity(plant);
                }
                return;
            }
        }
    }
    
}
