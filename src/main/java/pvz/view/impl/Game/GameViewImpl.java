package pvz.view.impl.Game;

import pvz.controller.api.ViewListener;
import pvz.utilities.GameEntity;
import pvz.view.api.GameView;

import javax.swing.*;

import java.awt.*;
import java.util.Set;

public class GameViewImpl implements GameView {

    private final JFrame frame = new JFrame("Plants vs Zombies");
    private final GameToolBar toolBar = new GameToolBar();
    private final DrawPanel drawPanel = new DrawPanel();
    private final GridPanel gridPanel = new GridPanel();

    private final JLayeredPane layeredPane = new JLayeredPane();

    private static final int WIDTH = 720;
    private static final int HEIGHT = 400;

    public GameViewImpl() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        drawPanel.setBounds(0, 0, WIDTH, HEIGHT);
        gridPanel.setBounds(0, 0, WIDTH +1, HEIGHT+1);


        layeredPane.add(gridPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(drawPanel, JLayeredPane.PALETTE_LAYER);

        frame.add(toolBar, BorderLayout.NORTH);
        frame.add(layeredPane, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void show() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    @Override
    public void close() {
        frame.dispose();
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public void render(Set<GameEntity> entities, int suns, int kills) {
        drawPanel.updateMovingEntities(Set.copyOf(entities));
        toolBar.updateStats(suns, kills);
    }

    @Override
    public void setViewListener(ViewListener listener) {
        toolBar.setViewListener(listener);
        gridPanel.setViewListener(listener);
    }
}