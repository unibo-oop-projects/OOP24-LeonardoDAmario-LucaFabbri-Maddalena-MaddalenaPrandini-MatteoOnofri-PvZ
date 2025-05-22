package pvz.view.impl.Game;

import pvz.controller.api.ViewListener;
import pvz.utilities.GameEntity;
import pvz.view.api.GameView;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class GameViewImpl extends JPanel implements GameView {

    private final GameToolBar toolBar = new GameToolBar();
    private final DrawPanel drawPanel = new DrawPanel();
    private final GridPanel gridPanel = new GridPanel();
    private final JLayeredPane layeredPane = new JLayeredPane();

    private ViewListener listener;

    private static final int WIDTH = 720;
    private static final int HEIGHT = 400;

    public GameViewImpl() {
        this.setLayout(new BorderLayout());

        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        drawPanel.setBounds(0, 0, WIDTH, HEIGHT);
        gridPanel.setBounds(0, 0, WIDTH + 1, HEIGHT + 1);

        layeredPane.add(gridPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(drawPanel, JLayeredPane.PALETTE_LAYER);

        this.add(toolBar, BorderLayout.NORTH);
        this.add(layeredPane, BorderLayout.CENTER);
    }


    @Override
    public void close() {

    }

    @Override
    public boolean isVisible() {
        return super.isVisible();
    }


    @Override
    public void render(Set<GameEntity> entities, int suns, int kills) {
        System.out.println("[DEBUG] render chiamato con " + entities.size() + " entità");
        drawPanel.updateMovingEntities(Set.copyOf(entities));
        toolBar.updateStats(suns, kills);

        // 🔁 forza repaint
        SwingUtilities.invokeLater(() -> {
            drawPanel.repaint();
            gridPanel.repaint();
            this.revalidate();
            this.repaint();
        });
    }


    @Override
    public void setViewListener(ViewListener listener) {
        this.listener = listener;
        toolBar.setViewListener(listener);
        gridPanel.setViewListener(listener);
    }
}
