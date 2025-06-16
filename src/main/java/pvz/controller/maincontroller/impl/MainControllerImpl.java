package pvz.controller.maincontroller.impl;

import pvz.controller.endgamecontroller.api.EndGameController;
import pvz.controller.endgamecontroller.impl.EndGameControllerImpl;
import pvz.controller.gamecontroller.api.GameController;
import pvz.controller.gamecontroller.impl.GameControllerImpl;
import pvz.controller.maincontroller.api.MainController;
import pvz.controller.menucontroller.api.MenuController;
import pvz.controller.menucontroller.impl.MenuControllerImpl;
import pvz.model.game.api.Difficulty;
import pvz.utilities.Resolution;

/**
 * Implementation of the {@link MainController} interface.
 * Manages the flow of the application: menu, game, end-game, and error handling.
 */
public final class MainControllerImpl implements MainController {

    /** The game controller instance. */
    private final GameController gameController = new GameControllerImpl(this);

    /** The menu controller instance. */
    private final MenuController menuController = new MenuControllerImpl(this);

    /** The end-game controller instance. */
    private final EndGameController endGameController = new EndGameControllerImpl(this);

    /**
     * Starts the application by opening the main menu.
     */
    @Override
    public void start() {
        menuController.openMenu();
    }

    /**
     * Starts the game with the given difficulty and resolution.
     *
     * @param difficulty the selected game difficulty
     * @param resolution the selected resolution
     */
    @Override
    public void startGame(final Difficulty difficulty, final Resolution resolution) {
        gameController.startGame(difficulty, resolution);
    }

    /**
     * Opens the main menu view.
     */
    @Override
    public void goToMenu() {
        menuController.openMenu();
    }

    /**
     * Navigates to the end-game screen.
     *
     * @param hasWon true if the player has won, false otherwise
     */
    @Override
    public void goToEndGame(final boolean hasWon) {
        endGameController.openEndGameMenu(hasWon);
    }

    /**
     * Handles any uncaught exceptions, prints the stack trace, and exits the application.
     *
     * @param exception the exception to handle
     */
    @Override
    public void handleException(final Exception exception) {
        exception.printStackTrace();
        System.err.println("Errore inaspettato, il gioco verrà chiuso. Controllare il log per maggiori informazioni.");
        System.exit(1);
    }
}
