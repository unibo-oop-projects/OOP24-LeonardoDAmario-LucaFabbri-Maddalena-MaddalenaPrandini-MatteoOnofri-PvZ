package PvZ.view.impl.Plants;

import PvZ.controller.api.ViewListener;
import PvZ.model.api.Plants.PlantType;
import PvZ.model.impl.Cell;
import PvZ.utilities.Position;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    private static final int ROWS = 5;
    private static final int COLUMNS = 9;
    private final JButton[][] cells;
    private int row, col;

    public Grid(ViewListener listener) {
        setLayout(new GridLayout(ROWS, COLUMNS));
        cells = new JButton[ROWS][COLUMNS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                JButton cell = new JButton();
                cell.setBackground(Color.LIGHT_GRAY);
                row = i;
                col = j;
                cell.addActionListener(e -> listener.processInputGrid(new Position(row, col)));
                cells[i][j] = cell;
                this.add(cell);
            }
        }
    }
}
