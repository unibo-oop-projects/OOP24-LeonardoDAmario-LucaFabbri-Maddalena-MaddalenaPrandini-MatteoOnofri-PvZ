package PvZ.model.impl;

import java.util.List;
import PvZ.model.api.Entity;
import PvZ.model.api.GameModel;

public final class GameModelImpl implements GameModel {
    private List<Entity> entities;
    
    @Override
    public void startGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startGame'");
    }

    @Override
    public void updateGame() {
        this.entities.forEach(Entity::update);
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
}