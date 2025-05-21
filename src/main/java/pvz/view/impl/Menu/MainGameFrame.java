package pvz.view.impl.Menu;

import pvz.controller.api.GameController;
import pvz.controller.api.ViewListener;
import pvz.controller.impl.GameControllerImpl;
import pvz.model.api.Difficulty;
import pvz.model.api.GameModel;
import pvz.model.impl.GameModelImpl;
import pvz.view.api.GameView;
import pvz.view.impl.Game.GameViewImpl;
import PvZ.PvZ;
import PvZ.controller.api.GameController;
import PvZ.controller.api.ViewListener;
import PvZ.controller.impl.GameControllerImpl;
import PvZ.model.api.Difficulty;
import PvZ.model.api.GameModel;
import PvZ.model.impl.GameModelImpl;
import PvZ.view.api.GameView;
import PvZ.view.impl.Game.GameViewImpl;

import javax.swing.*;
import java.awt.*;

public class MainGameFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    public static final String MENU = "Menu";

    public MainGameFrame() {
        super("Piante contro Zombie");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        this.add(mainPanel);
    }

    public void addMenuView(MenuView view) {
        mainPanel.add(view, MENU);
        cardLayout.show(mainPanel, MENU);
    }

    public void showGameView(JPanel gameView) {
        mainPanel.add(gameView, "Game");
        cardLayout.show(mainPanel, "Game");
    }

    public void startGame(Difficulty difficulty) {
        System.out.println("[SISTEMA]: Avvio gioco con difficoltà: " + difficulty);

        GameModel model = new GameModelImpl();
        GameView view = new GameViewImpl();
        GameController controller = new GameControllerImpl(model, view);
        view.setViewListener((ViewListener) controller);


        this.setVisible(false);
        controller.startGame();
    }

    public void returnToMenu() {
        this.dispose();
        PvZ.startGame();
    }
}
