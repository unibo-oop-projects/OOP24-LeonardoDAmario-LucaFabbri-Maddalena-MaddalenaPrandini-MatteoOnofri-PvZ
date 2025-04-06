package PvZ.model.impl;
import PvZ.model.api.EntitiesManager;
import PvZ.model.api.Entity;
import PvZ.model.api.GameModel;
public class GameModelImpl implements GameModel {
    EntitiesManager entitiesManager;

    public GameModelImpl(EntitiesManager entitiesManager) {
        this.entitiesManager = entitiesManager;
    }

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
        this.entitiesManager.getEntities().forEach(e->e.update(deltaTime, entitiesManager));
    }
    
    

}