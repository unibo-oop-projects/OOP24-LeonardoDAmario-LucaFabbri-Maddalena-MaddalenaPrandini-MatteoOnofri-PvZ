package PvZ.model.impl;
import PvZ.model.api.EntitiesManager;
import PvZ.model.api.Plant;
import PvZ.model.api.Entity;
import PvZ.model.api.GameModel;
import java.util.*;

public class GameModelImpl implements GameModel {
    EntitiesManager entitiesManager;

    private static final int ROWS = 5;
    private static final int COLS = 9;
    private static final int DEFAULTSUNS = 50

    private final List<List<Cell>> grid;
    private final List<Zombie> zombies;
    private final List<Plant> plants;

    private int sunCount;
    //private GameStatus status;

    private long lastSunTime;
    private long lastZombieSpawnTime;

    public GameModelImpl(EntitiesManager entitiesManager) {
        this.entitiesManager = entitiesManager;

        this.grid = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < COLS; j++) {
                row.add(new Cell());
            }
            grid.add(row);
        }

        this.zombies = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.sunCount = DEFAULTSUNS;
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