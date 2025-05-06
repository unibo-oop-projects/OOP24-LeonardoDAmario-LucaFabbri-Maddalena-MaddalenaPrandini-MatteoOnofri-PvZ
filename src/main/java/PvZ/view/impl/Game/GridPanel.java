package PvZ.view.impl.Game;

import PvZ.model.api.Plants.Plant;
import PvZ.model.api.Plants.PlantType;
import PvZ.utilities.EntityType;
import PvZ.utilities.GameEntity;
import PvZ.utilities.Position;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class GridPanel extends JPanel {

    private static final int ROWS = 5;
    private static final int COLS = 9;
    private static final int CELL_SIZE = 80;
    private final int leftMargin;
    private final JButton[][] cells = new JButton[ROWS][COLS];
    private final BiConsumer<Integer, Integer> cellClickHandler;
    private boolean hasSelection = false;

    public GridPanel(BiConsumer<Integer, Integer> cellClickHandler, int leftMargin) {
        this.cellClickHandler = cellClickHandler;
        this.leftMargin = leftMargin;
        setPreferredSize(new Dimension(leftMargin + COLS * CELL_SIZE, ROWS * CELL_SIZE));
        setLayout(null);
        initGrid();
    }

    private void initGrid() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                JButton btn = new JButton();
                btn.setEnabled(false);
                btn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                btn.setOpaque(true);
                btn.setBackground(Color.WHITE);
                final int row = r;
                final int col = c;
                btn.setBounds(leftMargin + c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                btn.addActionListener(e -> cellClickHandler.accept(row, col));
                cells[r][c] = btn;
                add(btn);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.translate(leftMargin, 0);
        GridRender.drawGrid(g2, ROWS, COLS, CELL_SIZE);
        GridRender.drawEntities(g2, entities, CELL_SIZE);
        g2.dispose();
    }

    public void updateEntities(Set<GameEntity> entities) {
        entities = new HashSet<>(entities.stream().filter(entity -> entity.type() == EntityType.PEASHOOTER
            || entity.type() == EntityType.SUNFLOWER || entity.type() == EntityType.WALLNUT ).collect(Collectors.toSet()));
        boolean[][] occupied = new boolean[ROWS][COLS];
        for (GameEntity e : entities) {
            Position p = e.position();
            if (p.x() >= 0 && p.x() < ROWS && p.y() >= 0 && p.y() < COLS) {
                occupied[(int)p.x()][(int)p.y()] = true;
            }
        }
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                JButton btn = cells[r][c];
                if (occupied[r][c]) {
                    btn.setEnabled(false);
                    btn.setText("X");
                } else {
                    btn.setEnabled(hasSelection);
                    btn.setText("");
                }
            }
        }
        repaint();
    }

    public void setSelectedPlant(PvZ.model.api.Plants.PlantType type) {
        hasSelection = (type != null);
    }
}