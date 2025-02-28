package PvZ.model.api;

public interface GameModel {
    void startGame();
    void updateGame();
    boolean isGameOver();
    boolean isVictory();
}
