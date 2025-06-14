package pvz.view.gameview.impl;

import pvz.controller.gamecontroller.api.ViewListener;
import pvz.utilities.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridPanel extends JPanel {

    private static final int ROWS = 5;
    private static final int COLS = 9;
    private static int cell_size;
    private static final int MARGIN_X = 0;
    private static final int MARGIN_Y = 0;

    private ViewListener listener;

    public GridPanel(double scaling) {
        this.setOpaque(false);
        initMouseListener();
        scaling = scaling * 80;
        cell_size = (int)scaling;
    }

    private void initMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = (e.getX() - MARGIN_X) / cell_size;
                int y = (e.getY() - MARGIN_Y) / cell_size;

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

        int totalWidth = COLS * cell_size;
        int totalHeight = ROWS * cell_size;

        for (int x = 0; x <= COLS; x++) {
            int xPos = MARGIN_X + x * cell_size;
            g2.drawLine(xPos, MARGIN_Y, xPos, MARGIN_Y + totalHeight);
        }

        for (int y = 0; y <= ROWS; y++) {
            int yPos = MARGIN_Y + y * cell_size;
            g2.drawLine(MARGIN_X, yPos, MARGIN_X + totalWidth, yPos);
        }
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MARGIN_X * 2 + COLS, MARGIN_Y * 2 + ROWS);
    }

}