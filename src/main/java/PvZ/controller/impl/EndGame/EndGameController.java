package pvz.controller.impl.EndGame;

import pvz.view.impl.Menu.MainGameFrame;
import pvz.view.impl.EndGame.EndGameView;

import java.awt.event.ActionEvent;

public class EndGameController {

    public EndGameController(MainGameFrame frame, boolean hasWon) {
        EndGameView view = new EndGameView(hasWon);
        frame.setContentPane(view);
        frame.revalidate();

        view.backToMenuButton.addActionListener((ActionEvent e) -> frame.returnToMenu());
        view.exitButton.addActionListener((ActionEvent e) -> System.exit(0));
    }
}
