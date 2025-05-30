/*
package pvz.view.excluded;

import pvz.controller.gamecontroller.api.GameController;
import pvz.controller.gamecontroller.api.ViewListener;
import pvz.controller.endgamecontroller.impl.EndGameControllerImpl;
import pvz.controller.gamecontroller.impl.GameControllerImpl;
import pvz.controller.menucontroller.impl.MenuControllerImpl;
import pvz.model.game.api.Difficulty;
import pvz.model.game.api.GameModel;
import pvz.model.game.impl.GameModelImpl;
import pvz.utilities.Resolution;
import pvz.view.endgameview.impl.EndGameViewImpl;
import pvz.view.gameview.impl.GameViewImpl;
import pvz.view.menuview.impl.MenuViewImpl;
import pvz.view.menuview.impl.TutorialView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainGameFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    Resolution resolution = Resolution.R800x600;
    private final Map<String, JPanel> views = new HashMap<>();

    public static final String MENU = "Menu";
    public static final String GAME = "Game";
    public static final String END = "End";
    public static final String TUTORIAL = "Tutorial";



    public MainGameFrame() {
        super("Piante contro Zombie");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        this.setContentPane(mainPanel);

    }


    public static void launchGame() {
        MainGameFrame frame = new MainGameFrame();
        frame.setVisible(true);
    }



    public void addMenuView(MenuViewImpl view) {
        //views.put(MENU, view);
        mainPanel.add(view, MENU);
        cardLayout.show(mainPanel, MENU);
    }

    public void showTutorialView() {
        TutorialView tutorialView = new TutorialView(this);
        views.put(TUTORIAL, tutorialView);
        mainPanel.add(tutorialView, TUTORIAL);
        cardLayout.show(mainPanel, TUTORIAL);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void showEndGameView(boolean isVictory) {
        EndGameViewImpl endGameView = new EndGameViewImpl(isVictory, this);
        new EndGameControllerImpl(endGameView, this);
        views.put(END, endGameView);
        mainPanel.add(endGameView, END);
        cardLayout.show(mainPanel, END);
        mainPanel.revalidate();
        mainPanel.repaint();
    }



    public void startGame(Difficulty difficulty) {

        if (views.containsKey(GAME)) {
            mainPanel.remove(views.get(GAME));
            views.remove(GAME);
        }

        GameModel model = new GameModelImpl(difficulty);
        GameViewImpl view = new GameViewImpl(this.getSize().width, this.getSize().height);
        GameController controller = new GameControllerImpl(model, view, this);
        view.setViewListener((ViewListener) controller);

        views.put(GAME, view);
        mainPanel.add(view, GAME);

        SwingUtilities.invokeLater(() -> {
            cardLayout.show(mainPanel, GAME);
            mainPanel.revalidate();
            mainPanel.repaint();
            controller.startGame();
        });
    }


    public void returnToMenu() {
        if (views.containsKey(MENU)) {
            mainPanel.remove(views.get(MENU));
            views.remove(MENU);
        }


        cardLayout.show(mainPanel, MENU);
        mainPanel.revalidate();
        mainPanel.repaint();
    }


}
*/