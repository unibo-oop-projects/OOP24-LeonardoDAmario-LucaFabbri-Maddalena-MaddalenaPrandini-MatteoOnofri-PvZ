package PvZ.model.api.GameBoard;

public interface SunCounter {
    int getSuns();
    void addSuns(int amount);
    boolean spendSuns(int amount);
}
