package PvZ.view.impl.Game.grid;

import PvZ.utilities.EntityType;
import PvZ.utilities.GameEntity;
import PvZ.utilities.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Set;

public class GridPanel extends JPanel {
    public static final int CELL_SIZE = 80;
    private static final int ROWS = 5;
    private static final int COLS = 9;

    private Set<GameEntity> entities;

    public GridPanel(ActionListener listener) {
        setLayout(null); // layout libero
        setPreferredSize(new Dimension(COLS * CELL_SIZE, ROWS * CELL_SIZE));
        this.entities = Set.of();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                CellButton btn = new CellButton(i, j);
                btn.setBounds(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                btn.addActionListener(listener);
                this.add(btn);
            }
        }
    }

    public void setEntities(Set<GameEntity> entities) {
        this.entities = entities;

        boolean[][] hasPlant = new boolean[ROWS][COLS];
        for (GameEntity entity : entities) {
            if (entity.type() == EntityType.PEASHOOTER ||
                    entity.type() == EntityType.SUNFLOWER ||
                    entity.type() == EntityType.WALLNUT) {
                hasPlant[(int)entity.position().x()][(int)entity.position().y()] = true;
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Component comp = getComponentAt(j * CELL_SIZE + 1, i * CELL_SIZE + 1);
                if (comp instanceof CellButton button) {
                    button.setEnabled(!hasPlant[i][j]);
                }
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawEntities(g);
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= ROWS; i++) {
            g.drawLine(0, i * CELL_SIZE, COLS * CELL_SIZE, i * CELL_SIZE);
        }
        for (int j = 0; j <= COLS; j++) {
            g.drawLine(j * CELL_SIZE, 0, j * CELL_SIZE, ROWS * CELL_SIZE);
        }
    }

    private void drawEntities(Graphics g) {
        for (GameEntity entity : entities) {
            Position pos = entity.position();
            int x = (int) (pos.y() * CELL_SIZE);
            int y = (int) (pos.x() * CELL_SIZE);

            g.setColor(getColorForEntity(entity.type()));
            g.fillRect(x + 10, y + 10, CELL_SIZE - 20, CELL_SIZE - 20); // Rettangolo centrato
        }
    }

    private Color getColorForEntity(EntityType type) {
        return switch (type) {
            case PEASHOOTER -> Color.GREEN;
            case SUNFLOWER -> Color.YELLOW;
            case WALLNUT -> new Color(139, 69, 19);
            case ZOMBIE -> Color.GRAY;
            case BULLET -> Color.BLACK;
        };
    }
}
