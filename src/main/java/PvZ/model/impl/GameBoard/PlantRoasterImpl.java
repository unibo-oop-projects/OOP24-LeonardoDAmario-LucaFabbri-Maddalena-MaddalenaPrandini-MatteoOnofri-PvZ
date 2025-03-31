package PvZ.model.impl.GameBoard;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import PvZ.model.api.GameBoard.PlantRoaster;
import PvZ.model.impl.Plant;

/**
 * This class implements the PlantRoaster interface.
 */
public final class PlantRoasterImpl implements PlantRoaster {

    private final List<Plant> availablePlants = new LinkedList<>();

    @Override
    public List<Plant> getAvailablePlants(final int sun) {
        if (sun <= 0) {
            return List.of();
        }
        if (this.availablePlants.isEmpty()) {
            return List.of();
        }

        return this.availablePlants.stream()
            .filter(plant -> plant.getType().getPrice() <= sun)
            .toList();
    }

    @Override
    public void addPlant(final Plant plant) {
        Objects.requireNonNull(plant, "Plant cannot be null");
        this.availablePlants.add(plant);
    }
    
}
