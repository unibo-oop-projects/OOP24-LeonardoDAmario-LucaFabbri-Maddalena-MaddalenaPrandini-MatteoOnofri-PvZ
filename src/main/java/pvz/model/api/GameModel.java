package pvz.model.api;

import pvz.model.api.plants.PlantType;
import pvz.utilities.GameEntity;
import pvz.utilities.Position;

import java.util.Set;

public interface GameModel {
    void startGame();
    boolean isGameOver();
    boolean isVictory();
    GameStatus getGameStatus();
    void placePlant(PlantType type, Position position);
    void update(long deltaTime);
    Set<GameEntity> getGameEntities();
    int getSunCount();
    int getKillCount();
}
