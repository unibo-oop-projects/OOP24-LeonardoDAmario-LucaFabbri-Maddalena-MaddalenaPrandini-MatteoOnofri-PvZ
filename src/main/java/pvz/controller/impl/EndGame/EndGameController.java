package pvz.controller.impl.EndGame;

import pvz.view.impl.EndGameMenu.EndGameView;
import pvz.view.impl.Game.MainGameFrame;

public class EndGameController {

    public EndGameController(EndGameView view, MainGameFrame frame) {
        view.getBackToMenuButton().addActionListener(e -> frame.returnToMenu());
        view.getExitButton().addActionListener(e -> System.exit(0));
    }
}
