package pvz.controller.endgamecontroller.api;

import pvz.controller.maincontroller.api.Controller;

/**
 * Interface for the controller that manages the end-game menu.
 * Handles opening and closing of the end-game screen.
 */
public interface EndGameController extends Controller {

    /**
     * Opens the end-game menu showing victory or defeat.
     *
     * @param hasWon true if the player has won, false otherwise
     */
    void openEndGameMenu(boolean hasWon);

    /**
     * Closes the end-game menu and returns to the main menu.
     */
    void closeEndGameMenu();
}
