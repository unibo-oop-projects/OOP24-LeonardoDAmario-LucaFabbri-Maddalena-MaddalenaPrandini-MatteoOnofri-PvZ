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

public class MainControllerImpl implements MainController {

    private final GameController gameController = new GameControllerImpl(this);
    private final MenuController menuController = new MenuControllerImpl(this);
    private final EndGameController endGameController = new EndGameControllerImpl(this);

    @Override
        public void start() {
            menuController.openMenu();
        }

        @Override
        public void quit() {

        }

        public void startGame(Difficulty difficulty, Resolution resolution) {
            gameController.startGame(difficulty, resolution);
        }

        public void goToMenu() {
            menuController.openMenu();
        }

        public void goToEndGame(boolean hasWon) {
            endGameController.openEndGameMenu(hasWon);
        }


        @Override
        public void handleException(Exception exception) {

        }
}
