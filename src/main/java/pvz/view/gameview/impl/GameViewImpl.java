package pvz.view.gameview.impl;

import pvz.controller.gamecontroller.api.GameController;
import pvz.controller.gamecontroller.api.ViewListener;
import pvz.utilities.GameEntity;
import pvz.utilities.Resolution;
import pvz.view.gameview.api.GameView;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * Implementation of the {@link GameView} interface.
 * This class is responsible for setting up and managing the visual elements of the game,
 * including the toolbar, game grid, and drawable entities.
 * It also connects the view to the controller via the {@link ViewListener} interface.
 */
public class GameViewImpl extends JPanel implements GameView {

    /**
     * The game's toolbar displaying controls and stats.
     */
    private final GameToolBar toolBar = new GameToolBar();

    /**
     * Panel used to draw moving and stationary entities (plants, zombies, bullets, etc.).
     */
    private final DrawPanel drawPanel;

    /**
     * Panel representing the clickable game grid where the player can place plants.
     */
    private final GridPanel gridPanel;

    /**
     * LayeredPane to overlap the grid and drawable components (grid is below, drawPanel above).
     */
    private final JLayeredPane layeredPane = new JLayeredPane();

    /**
     * The main application window.
     */
    private final JFrame frame = new JFrame();

    /**
     * The chosen game resolution used to scale UI elements.
     */
    private final Resolution resolution;

    /**
     * The controller that manages game logic and communication with the view.
     */
    private final GameController parentController;

    /**
     * Listener that receives user input events from the view.
     */
    private ViewListener listener;

    /**
     * Constructs a new GameViewImpl instance.
     *
     * @param controller The game controller to communicate with.
     * @param resolution The chosen resolution for the game window.
     */
    public GameViewImpl(GameController controller, Resolution resolution) {
        this.parentController = controller;
        this.resolution = resolution;
        double scaling = 0.8 * resolution.getWidth() / 640.0;

        this.drawPanel = new DrawPanel(scaling);
        this.gridPanel = new GridPanel(scaling);
        layeredPane.setLayout(new OverlayLayout(layeredPane));

        layeredPane.setPreferredSize(new Dimension(resolution.getWidth(), resolution.getHeight()));
        drawPanel.setBounds(0, 0, resolution.getWidth(), resolution.getHeight());

        layeredPane.add(gridPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(drawPanel, JLayeredPane.PALETTE_LAYER);

        this.add(toolBar, BorderLayout.NORTH);
        this.add(layeredPane, BorderLayout.CENTER);

        configureFrame();
        setViewListener((ViewListener) this.parentController);

    }

    /**
     * Closes the application window and disposes of the frame.
     */
    @Override
    public void close() {
        this.frame.dispose();
    }

    /**
     * Returns whether the view is currently visible.
     *
     * @return true if the view is visible; false otherwise.
     */
    @Override
    public boolean isVisible() {
        return super.isVisible();
    }

    /**
     * Renders the game state, including entity positions and game statistics.
     *
     * @param entities The set of entities to render (plants, zombies, etc.).
     * @param suns     The current sun count.
     * @param kills    The number of zombies defeated.
     */
    @Override
    public void render(Set<GameEntity> entities, int suns, int kills) {
        drawPanel.updateMovingEntities(Set.copyOf(entities));
        toolBar.updateStats(suns, kills);

        SwingUtilities.invokeLater(() -> {
            drawPanel.repaint();
            gridPanel.repaint();
            this.revalidate();
            this.repaint();
        });
    }

    /**
     * Assigns a listener to receive user input events from the view.
     *
     * @param listener The {@link ViewListener} instance.
     */
    @Override
    public void setViewListener(ViewListener listener) {
        this.listener = listener;
        toolBar.setViewListener(listener);
        gridPanel.setViewListener(listener);
    }

    /**
     * Configures the main application window with title, size, and visibility.
     */
    private void configureFrame() {
        frame.setTitle("Piante contro Zombie");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(this.resolution.getWidth(), this.resolution.getHeight());
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
