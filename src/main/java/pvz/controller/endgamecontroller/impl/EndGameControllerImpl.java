package pvz.controller.endgamecontroller.impl;

import pvz.controller.endgamecontroller.api.EndGameController;
import pvz.controller.maincontroller.api.MainController;
import pvz.view.endgameview.impl.EndGameViewImpl;


public class EndGameControllerImpl implements EndGameController {

    private final MainController parentController;
    private EndGameViewImpl view;

    public EndGameControllerImpl(MainController controller) {
        this.parentController = controller;

    }

    @Override
    public void openEndGameMenu(boolean hasWon) {
        this.view = new EndGameViewImpl(this, hasWon);
    }

    @Override
    public void closeEndGameMenu() {
        parentController.goToMenu();
        view.close();
    }

    @Override
    public void handleException(Exception exception) {
        parentController.handleException(exception);
    }
}
