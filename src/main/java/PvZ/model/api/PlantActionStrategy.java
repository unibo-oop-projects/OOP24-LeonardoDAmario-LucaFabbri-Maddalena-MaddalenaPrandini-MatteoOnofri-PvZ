package PvZ.model.api;

public interface PlantActionStrategy {
    void plantAction(BasePlant plant);
    int getInitialLife();
}
