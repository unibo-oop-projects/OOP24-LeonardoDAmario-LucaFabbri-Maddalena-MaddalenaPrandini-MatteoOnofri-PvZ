package PvZ.model.api;

public interface PlantActionStrategy {
    void plantAction(Plant plant);
    int getInitialLife();
}
