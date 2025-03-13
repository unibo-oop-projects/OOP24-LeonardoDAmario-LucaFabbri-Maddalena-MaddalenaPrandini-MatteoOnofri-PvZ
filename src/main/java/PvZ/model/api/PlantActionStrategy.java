package PvZ.model.api;

public interface PlantActionStrategy {
    void PlantAction(BasePlant plant);
    int getInitialLife();
}
