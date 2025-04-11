package PvZ.model.api;

public interface GameModel {
    void startGame();
    boolean isGameOver();
    boolean isVictory();
    GameStatus getGameStatus();
    void update(long deltaTime);
}
