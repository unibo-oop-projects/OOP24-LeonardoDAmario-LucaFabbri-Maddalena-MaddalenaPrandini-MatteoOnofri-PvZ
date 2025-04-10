package PvZ.model.api;

public interface GameModel {
    void startGame();
    boolean isGameOver();
    boolean isVictory();
    GameStatus getGameStatus();
    public void addSun(int amount);
    public boolean spendSun(int amount);
    void update(long deltaTime);
}
