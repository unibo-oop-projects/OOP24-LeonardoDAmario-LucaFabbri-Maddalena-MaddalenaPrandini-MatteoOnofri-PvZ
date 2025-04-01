package PvZ.model.impl;

import java.util.List;
import java.util.stream.Stream;

import PvZ.model.api.BasePlant;
import PvZ.model.api.Bullet;
import PvZ.model.api.Entity;
import PvZ.model.api.GameModel;
import PvZ.model.api.Sun;
import PvZ.model.api.Zombie;
import PvZ.model.impl.Plants.PeaShooterStrategy;
import PvZ.model.impl.Plants.Plant;
import PvZ.model.impl.Plants.SunflowerStrategy;
import PvZ.model.impl.Plants.WallNutStrategy;
public class GameModelImpl implements GameModel {

    private List<BasePlant> plants;
    private List<Zombie> zombies;
    private List<Bullet> bullets;
    private SunCounter sunCounter;

    private static final long SUNFLOWER_TIME = 5000;
    private long sunFlowerTime = 0;

    @Override
    public void startGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startGame'");
    }

    @Override
    public boolean isGameOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isGameOver'");
    }

    @Override
    public boolean isVictory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isVictory'");
    }

    @Override
    public void update(long deltaTime) {
        sunFlowerTime += deltaTime;
        if(sunFlowerTime >= SUNFLOWER_TIME) {
            this.updateSunFlower();
            sunFlowerTime = 0;
        }
    }

    void updateWalnut() {
        plants.stream().filter(plant -> plant instanceof WallNutStrategy).forEach(walNut -> walNut.update());
    }

    void updatePeaShooter() {
        plants.stream().filter(plant -> plant instanceof PeaShooterStrategy).forEach(peaShooter -> peaShooter.update());
    }   

    void updateSunFlower() {
        plants.stream().filter(plant -> plant instanceof SunflowerStrategy).forEach(sunFlower -> sunFlower.update());
    }   

    void updateZombies() {
        zombies.forEach(zombie -> {
            if(plants.stream().anyMatch(plant -> plant.getPosition().equals(zombie.getPosition()))) {
                final BasePlant plant = plants.stream().filter(t -> t.getPosition().equals(zombie.getPosition())).findAny().get();
                plant.decreaseLife();
            }
            else {
                zombie.update();
            }
        });
    }

    void updateBullets() {
        bullets.forEach(bullet -> {
            if(zombies.stream().anyMatch(zombie -> zombie.getPosition().equals(bullet.getPosition()))) {
                final Zombie zombie = zombies.stream().filter(t -> t.getPosition().equals(bullet.getPosition())).findAny().get();
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