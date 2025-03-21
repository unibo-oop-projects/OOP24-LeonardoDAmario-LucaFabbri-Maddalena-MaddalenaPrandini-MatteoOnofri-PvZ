package PvZ.model.impl;

import java.util.List;
import java.util.stream.Stream;

import PvZ.model.api.Entity;
import PvZ.model.api.GameModel;
import PvZ.model.api.Zombie;
public class GameModelImpl implements GameModel {
    private static List<Entity> entities;
    private static SunCounter sunCounter;
    
    @Override
    public void startGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startGame'");
    }

    @Override
    public void updateGame() {
        for(int i=0; i<entities.size(); i++){
            entities.get(i).update();
        }
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

    public static SunCounter getSunCounter() {
        return sunCounter;
    }

    public static List<Zombie> getZombieList() {
        return entities.stream().filter(entity -> entity instanceof Zombie).map(entity -> (Zombie) entity).toList();
    }

}