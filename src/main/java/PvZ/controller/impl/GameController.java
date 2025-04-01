package PvZ.controller.impl;

import java.util.List;

import PvZ.model.api.BasePlant;
import PvZ.model.api.Bullet;
import PvZ.model.api.Entity;
import PvZ.model.api.Sun;
import PvZ.model.api.Zombie;
import PvZ.model.impl.GameModelImpl;
import PvZ.model.impl.SunCounter;
import PvZ.model.impl.SunImpl;
import PvZ.model.impl.Plants.PeaShooterStrategy;
import PvZ.model.impl.Plants.SunflowerStrategy;

public class GameController {

    private final GameModelImpl currentGame;

    GameController(final GameModelImpl game) {
        this.currentGame = game;
    }

    void updatePeaShooter() {
        final List<BasePlant> peaShooterList = this.currentGame.getEntityList().stream()
                                        .filter(entity -> entity instanceof PeaShooterStrategy)
                                        .map(entity -> (BasePlant) entity).toList();
        plantList.forEach(plant -> plant.update());
    }   

    void updateSunFlower() {
        final List<BasePlant> sunFlowerList = this.currentGame.getEntityList().stream()
                                        .filter(entity -> entity instanceof SunflowerStrategy)
                                        .map(entity -> (BasePlant) entity).toList();
        plantList.forEach(plant -> plant.update());
    }   

    void updateZombies() {
        final List<Zombie> zombieList = this.currentGame.getEntityList().stream().
                                        filter(entity -> entity instanceof Zombie)
                                        .map(entity -> (Zombie) entity).toList();
        final List<BasePlant> plantList = this.currentGame.getEntityList().stream()
                                        .filter(entity -> entity instanceof BasePlant)
                                        .map(entity -> (BasePlant) entity).toList();
        zombieList.forEach(zombie -> {
            if(plantList.stream().anyMatch(plant -> plant.getPosition().equals(zombie.getPosition()))) {
                final BasePlant plant = plantList.stream().filter(t -> t.getPosition().equals(zombie.getPosition())).findAny().get();
                plant.decreaseLife();
            }
            else {
                zombie.update();
            }
        });
    }

    void updateBullets() {
        final List<Zombie> zombieList = this.currentGame.getEntityList().stream().
                                        filter(entity -> entity instanceof Zombie)
                                        .map(entity -> (Zombie) entity).toList();
        final List<Bullet> bulletList = this.currentGame.getEntityList().stream()
                                        .filter(entity -> entity instanceof Bullet)
                                        .map(entity -> (Bullet) entity).toList();
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

    void updateSunCounter(final int value) {
        final SunCounter sunCounter = this.currentGame.getSunCounter();
        final List<Sun> sunList = sunCounter.getSunList();
        sunList.stream().forEach(sun -> {
            if(sun.canIncrementSunCounter()) {
                sunCounter.increment(sun.getSunValue());
            }
            if(!sun.isAlreadyWorking()) {
                sun.startSunTimer();
            }
        });
    }
}