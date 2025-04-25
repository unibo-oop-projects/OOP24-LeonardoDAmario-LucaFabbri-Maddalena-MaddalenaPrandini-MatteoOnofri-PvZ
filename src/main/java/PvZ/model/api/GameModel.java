package PvZ.model.api;

import PvZ.model.api.Plants.PlantType;
import PvZ.utilities.EntityType;
import PvZ.utilities.GameEntity;
import PvZ.utilities.Position;

import java.util.Set;

public interface GameModel {
    void startGame();
    boolean isGameOver();
    boolean isVictory();
    GameStatus getGameStatus();
    void placePlant(PlantType type, Position position);
    void update(long deltaTime);
    Set<GameEntity> getGameEntities();
}
