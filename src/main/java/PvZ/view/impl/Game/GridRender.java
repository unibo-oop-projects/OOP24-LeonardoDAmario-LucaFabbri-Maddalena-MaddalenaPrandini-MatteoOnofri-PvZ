package PvZ.view.impl.Game;

import PvZ.utilities.EntityType;
import PvZ.utilities.GameEntity;
import PvZ.utilities.Position;

import java.awt.*;
import java.util.Set;

public class GridRender {
    public static void drawGrid(Graphics g, int rows, int cols, int cellSize) {
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= rows; i++) {
            g.drawLine(0, i * cellSize, cols * cellSize, i * cellSize);
        }
        for (int i = 0; i <= cols; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, rows * cellSize);
        }
    }

    public static void drawEntities(Graphics g, Set<GameEntity> entities, int cellSize) {
        for (GameEntity e : entities) {
            Position p = e.position();
            int row = (int) p.x();
            int col = (int) p.y();
            if (row < 0 || col < 0) continue;

            int x = col * cellSize;
            int y = row * cellSize;
            g.setColor(getColor(e.type()));
            int size = cellSize / 2;
            int offset = (cellSize - size) / 2;
            g.fillRect(x + offset, y + offset, size, size);
        }
    }

    private static Color getColor(EntityType type) {
        return switch (type) {
            case PEASHOOTER -> Color.GREEN;
            case SUNFLOWER -> Color.YELLOW;
            case WALLNUT   -> new Color(139, 69, 19);
            case ZOMBIE    -> Color.RED;
            case BULLET    -> Color.BLACK;
        };
    }
}
