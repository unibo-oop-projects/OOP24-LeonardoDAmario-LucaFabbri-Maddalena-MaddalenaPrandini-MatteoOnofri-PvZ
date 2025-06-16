package pvz.controller.endgamecontroller.impl;

import pvz.controller.endgamecontroller.api.EndGameController;
import pvz.controller.maincontroller.api.MainController;
import pvz.view.endgameview.impl.EndGameViewImpl;

/**
 * Implementation of the {@link EndGameController} interface.
 * Handles logic for the end-game menu and transitions to and from it.
 */
public final class EndGameControllerImpl implements EndGameController {

    /** The parent main controller. */
    private final MainController parentController;

    /** The view for the end-game screen. */
    private EndGameViewImpl view;

    /**
     * Constructs the end-game controller with the given parent controller.
     *
     * @param controller the parent MainController instance
     */
    public EndGameControllerImpl(final MainController controller) {
        this.parentController = controller;
    }

    /**
     * Opens the end-game menu.
     *
     * @param hasWon true if the player has won, false otherwise
     */
    @Override
    public void openEndGameMenu(final boolean hasWon) {
        this.view = new EndGameViewImpl(this, hasWon);
    }

    /**
     * Closes the end-game menu and returns to the main menu.
     */
    @Override
    public void closeEndGameMenu() {
        parentController.goToMenu();
        view.close();
    }

    /**
     * Handles exceptions by delegating to the parent controller.
     *
     * @param exception the exception to handle
     */
    @Override
    public void handleException(final Exception exception) {
        parentController.handleException(exception);
    }
}
