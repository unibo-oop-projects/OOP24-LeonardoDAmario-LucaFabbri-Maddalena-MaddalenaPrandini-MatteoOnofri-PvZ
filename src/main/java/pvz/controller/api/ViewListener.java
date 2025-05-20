package pvz.controller.api;

import pvz.utilities.Position;

/**
 * The {@code ViewListener} interface defines the contract for handling user inputs
 * in the game's view layer. It acts as a listener that reacts to events such as
 * selecting a plant type or clicking on a grid position.
 */
public interface ViewListener {

    /**
     * Enum representing the available plant types a user can select.
     */
    enum UserInputRoaster {
        /** Represents the Peashooter plant selection. */
        PEASHOOTER,
        /** Represents the Sunflower plant selection. */
        SUNFLOWER,
        /** Represents the Wall-nut plant selection. */
        WALLNUT
    }

    /**
     * Processes the user input when a plant type is selected from the roaster.
     *
     * @param input the selected plant type
     */
    void processInputRoaster(UserInputRoaster input);

    /**
     * Processes the user input when a grid cell is selected.
     *
     * @param position the {@link Position} object representing the selected cell on the grid
     */
    void processInputGrid(Position position);

}
