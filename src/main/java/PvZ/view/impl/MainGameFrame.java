package PvZ.view.impl;

import PvZ.model.api.Difficulty;
import PvZ.view.impl.Menu.MenuView;

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

        System.out.println("Avvio gioco con difficoltà: " + difficulty);
    }
}
