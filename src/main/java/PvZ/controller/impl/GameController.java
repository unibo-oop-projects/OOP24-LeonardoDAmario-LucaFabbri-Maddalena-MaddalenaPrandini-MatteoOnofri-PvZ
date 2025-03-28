package PvZ.controller.impl;

import java.util.List;

import PvZ.model.api.BasePlant;
import PvZ.model.api.Bullet;
import PvZ.model.api.Entity;
import PvZ.model.api.Zombie;
import PvZ.model.impl.GameModelImpl;

public class GameController {

    private final GameModelImpl currentGame;

    GameController(final GameModelImpl game) {
        this.currentGame = game;
    }

    void update() {
        List<Entity> entityList = this.currentGame.getEntityList();
        List<BasePlant> plantList = entityList.stream().filter(entity -> entity instanceof BasePlant).map(entity -> (BasePlant) entity).toList();
        List<Bullet> bulletList = entityList.stream().filter(entity -> entity instanceof Bullet).map(entity -> (Bullet) entity).toList();
        List<Zombie> zombieList = entityList.stream().filter(entity -> entity instanceof Zombie).map(entity -> (Zombie) entity).toList();

        plantList.forEach(plant -> plant.update());
        zombieList.forEach(zombie -> {
            if(plantList.stream().anyMatch(plant -> plant.getPosition().equals(zombie.getPosition()))) {
                final BasePlant plant = plantList.stream().filter(t -> t.getPosition().equals(zombie.getPosition())).findAny().get();
                plant.decreaseLife();
            }
            else {
                zombie.update();
            }
        });
        bulletList.forEach(bullet -> {
            if(zombieList.stream().anyMatch(zombie -> zombie.getPosition().equals(bullet.getPosition()))) {
                final Zombie zombie = zombieList.stream().filter(t -> t.getPosition().equals(bullet.getPosition())).findAny().get();
                zombie.decreaseLife();
                bullet.die();
            }
            else {
                bullet.update();
            }
        });
    }   
}