package pvz.controller.endgamecontroller.impl;

import pvz.controller.endgamecontroller.api.EndGameController;
import pvz.view.impl.EndGameMenu.EndGameView;
import pvz.view.impl.Game.MainGameFrame;

public class EndGameControllerImpl implements EndGameController {

    public EndGameControllerImpl(EndGameView view, MainGameFrame frame) {
        view.getBackToMenuButton().addActionListener(e -> frame.returnToMenu());
        view.getExitButton().addActionListener(e -> System.exit(0));
    }

    @Override
    public void openEndGameMenu() {

    }

    @Override
    public void closeEndGameMenu() {

    }

    @Override
    public void quit() {

    }

    @Override
    public void handleException(Exception exception) {

    }
}
