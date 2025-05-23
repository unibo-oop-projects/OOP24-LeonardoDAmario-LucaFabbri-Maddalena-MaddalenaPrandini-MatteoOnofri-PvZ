package pvz.view.impl.Game;

import pvz.controller.api.GameController;
import pvz.controller.api.ViewListener;
import pvz.controller.impl.EndGame.EndGameController;
import pvz.controller.impl.GameControllerImpl;
import pvz.controller.impl.Menu.MenuController;
import pvz.model.api.Difficulty;
import pvz.model.api.GameModel;
import pvz.model.impl.GameModelImpl;
import pvz.view.impl.EndGameMenu.EndGameView;
import pvz.view.impl.Menu.MenuView;
import pvz.view.impl.Menu.TutorialView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class MainGameFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final Map<String, JPanel> views = new HashMap<>();
    private final double aspectRatio = 800.0 / 600.0;

    public static final String MENU = "Menu";
    public static final String GAME = "Game";
    public static final String END = "End";
    public static final String TUTORIAL = "Tutorial";



    public MainGameFrame() {
        super("Piante contro Zombie");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        this.setContentPane(mainPanel);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeDiagonally();
            }
        });
    }

    private void resizeDiagonally() {
        final Dimension size = getSize();
        final int newWidth = size.width;
        final int newHeight = (int) (newWidth / aspectRatio);

        // evita loop infiniti
        if (Math.abs(newHeight - size.height) > 1) {
            setSize(newWidth, newHeight);
        }
    }


    public static void launchGame() {
        MainGameFrame frame = new MainGameFrame();
        frame.initMenu();
        frame.setVisible(true);
    }

    public void initMenu() {
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController( menuView, this);

        this.addMenuView(menuView);
    }



    public void addMenuView(MenuView view) {
        views.put(MENU, view);
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
        EndGameView endGameView = new EndGameView(isVictory, this);
        new EndGameController(endGameView, this);
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
        GameViewImpl view = new GameViewImpl();
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

        initMenu();

        cardLayout.show(mainPanel, MENU);
        mainPanel.revalidate();
        mainPanel.repaint();
    }


}
