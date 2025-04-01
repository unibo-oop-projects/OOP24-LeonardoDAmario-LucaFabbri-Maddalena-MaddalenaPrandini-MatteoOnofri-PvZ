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
    private static final long ZOMBIE_TIME = 5000;
    private static final long BULLET_TIME = 5000;
    private static final long PEASHOOTERTIME_TIME = 5000;
    private static final long WALNUT_TIME = 5000;
    private long sunFlowerTime = 0;
    private long walNutTime = 0;
    private long peaShooterTime = 0;
    private long zombieTime = 0;
    private long bulletTime = 0;

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
        this.sunFlowerTime = controlTime(sunFlowerTime, deltaTime, SUNFLOWER_TIME, () -> this.updateSunFlowers());
        this.walNutTime = controlTime(walNutTime, deltaTime, WALNUT_TIME, () -> this.updateWalnuts());
        this.peaShooterTime = controlTime(peaShooterTime, deltaTime, PEASHOOTERTIME_TIME, () -> this.updatePeaShooters());
        this.zombieTime = controlTime(zombieTime, deltaTime, ZOMBIE_TIME, () -> this.updateZombies());
        this.bulletTime = controlTime(bulletTime, deltaTime, BULLET_TIME, () -> this.updateBullets());
    }

    private long controlTime(long entityTime, long deltaTime, long maxTime, Runnable updateMethod) {
        entityTime += deltaTime;
        if (entityTime >= maxTime) {
            updateMethod.run();
            return 0;
        }
        return entityTime;
    }

    void updateWalnuts() {
        plants.stream().filter(plant -> plant instanceof WallNutStrategy).forEach(walNut -> walNut.update());
    }

    void updatePeaShooters() {
        plants.stream().filter(plant -> plant instanceof PeaShooterStrategy).forEach(peaShooter -> peaShooter.update());
    }   

    void updateSunFlowers() {
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
    

}