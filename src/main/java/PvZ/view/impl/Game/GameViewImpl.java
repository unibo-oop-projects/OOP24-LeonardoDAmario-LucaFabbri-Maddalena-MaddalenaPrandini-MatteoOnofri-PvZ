package PvZ.view.impl.Game;

import PvZ.controller.api.ViewListener;
import PvZ.model.api.Plants.PlantType;
import PvZ.utilities.GameEntity;
import PvZ.utilities.Position;
import PvZ.view.api.GameView;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class GameViewImpl implements GameView {

    private static final int CELL_SIZE = 80;
    private static final int COLS = 9;
    private static final int ROWS = 5;
    private static final int LEFT_MARGIN = CELL_SIZE;

    private final JFrame frame;
    private final GamePanel panel;
    private final GameToolBar roaster;

    private ViewListener listener;
    private PlantType selectedPlant;
    private Set<GameEntity> lastEntities = new HashSet<>();

    public GameViewImpl() {
        frame = new JFrame("Plants vs Zombies - Java Edition");
        panel = new GamePanel(this::handleCellClick, LEFT_MARGIN);
        roaster = new GameToolBar(this::handlePlantSelection);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(LEFT_MARGIN + COLS * CELL_SIZE + 200, ROWS * CELL_SIZE + 100);
        frame.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(roaster);
        leftPanel.setPreferredSize(new Dimension(200, frame.getHeight()));

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(panel, BorderLayout.CENTER);
    }

    private void handlePlantSelection(PlantType type) {
        if (listener != null) {
            switch (type) {
                case PEASHOOTER -> listener.processInputRoaster(ViewListener.UserInputRoaster.PEASHOOTER);
                case SUNFLOWER -> listener.processInputRoaster(ViewListener.UserInputRoaster.SUNFLOWER);
                case WALLNUT -> listener.processInputRoaster(ViewListener.UserInputRoaster.WALLNUT);
            }
        }
        selectedPlant = type;
        panel.setSelectedPlant(type);
        panel.updateEntities(lastEntities);
    }

    private void handleCellClick(int row, int col) {
        if (selectedPlant != null && listener != null) {
            listener.processInputGrid(new Position(row, col));
            selectedPlant = null;
            panel.setSelectedPlant(null);
            panel.updateEntities(lastEntities);
        }
    }

    @Override
    public void show() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
            new Timer(1000 / 60, e -> panel.repaint()).start();
        });
    }

    @Override
    public void render(Set<GameEntity> entities, int suns, int kills) {
        roaster.statesUpdate(suns, kills);
        lastEntities = new HashSet<>(entities);

        panel.setSelectedPlant(selectedPlant);
        panel.updateEntities(entities);
    }

    @Override
    public void update() {
    }

    @Override
    public void close() {
        frame.dispose();
    }

    @Override
    public boolean isVisible() {
        return frame.isVisible();
    }

    @Override
    public void setViewListener(ViewListener listener) {
        this.listener = listener;
    }
}