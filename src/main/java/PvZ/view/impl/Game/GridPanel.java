package PvZ.view.impl.Game;

import PvZ.controller.api.ViewListener;
import PvZ.model.api.Plants.Plant;
import PvZ.model.api.Plants.PlantType;
import PvZ.utilities.EntityType;
import PvZ.utilities.GameEntity;
import PvZ.utilities.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class GridPanel extends JPanel {

    private static final int ROWS = 5;
    private static final int COLS = 9;
    private static final int CELL_SIZE = 80;
    private static final int MARGIN_X = 0;
    private static final int MARGIN_Y = 0;

    private ViewListener listener;

    public GridPanel() {
        this.setOpaque(false);
        initMouseListener();
    }

    private void initMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = (e.getX() - MARGIN_X) / CELL_SIZE;
                int y = (e.getY() - MARGIN_Y) / CELL_SIZE;

                if (x >= 0 && x < COLS && y >= 0 && y < ROWS && listener != null) {
                    listener.processInputGrid(new Position(x, y));
                }
            }
        });
    }

    public void setViewListener(ViewListener listener) {
        this.listener = listener;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setColor(Color.BLACK);

        int totalWidth = COLS * CELL_SIZE;
        int totalHeight = ROWS * CELL_SIZE;

        for (int x = 0; x <= COLS; x++) {
            int xPos = MARGIN_X + x * CELL_SIZE;
            g2.drawLine(xPos, MARGIN_Y, xPos, MARGIN_Y + totalHeight);
        }

        for (int y = 0; y <= ROWS; y++) {
            int yPos = MARGIN_Y + y * CELL_SIZE;
            g2.drawLine(MARGIN_X, yPos, MARGIN_X + totalWidth, yPos);
        }
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MARGIN_X * 2 + COLS, MARGIN_Y * 2 + ROWS);
    }

}