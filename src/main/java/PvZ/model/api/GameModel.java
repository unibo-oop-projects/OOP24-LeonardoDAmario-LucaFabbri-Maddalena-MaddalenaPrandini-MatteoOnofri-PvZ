package PvZ.model.api;

public interface GameModel {
    void startGame();
    boolean isGameOver();
    boolean isVictory();
    void update(long deltaTime);
}
