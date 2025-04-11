package PvZ.model.impl;
import PvZ.model.api.*;

import java.util.*;

public class GameModelImpl implements GameModel {
    EntitiesManager entitiesManager;

    private static final int ROWS = 5;
    private static final int COLS = 9;

    private final List<List<Cell>> grid;

    private GameStatus status;

    private long lastSunTime;
    private long lastZombieSpawnTime;

    public GameModelImpl() {
        this.entitiesManager = new EntitiesManagerImpl();

        this.grid = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < COLS; j++) {
                row.add(new Cell());
            }
            grid.add(row);
        }
        this.status = GameStatus.IN_PROGRESS;
        this.lastSunTime = System.currentTimeMillis();
        this.lastZombieSpawnTime = System.currentTimeMillis();
    }

    @Override
    public void startGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startGame'");
    }

    @Override
    public boolean isGameOver() {
            return status != GameStatus.IN_PROGRESS;
    }

    @Override
    public boolean isVictory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isVictory'");
    }

    @Override
    public GameStatus getGameStatus() {
        return status;
    }


    @Override
    public void update(long deltaTime) {
        this.entitiesManager.getEntities().forEach(e->e.update(deltaTime, entitiesManager));
    }
    
    

}