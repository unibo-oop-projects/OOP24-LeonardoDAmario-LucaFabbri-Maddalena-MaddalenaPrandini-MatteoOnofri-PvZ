package PvZ.view.impl.Plants;

import PvZ.controller.api.ViewListener;
import PvZ.utilities.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Grid extends JPanel {

    private static final int ROWS = 5;
    private static final int COLUMNS = 9;
    private final CellButton[][] buttons = new CellButton[ROWS][COLUMNS];

    public Grid(ActionListener listener) {
        setLayout(new GridLayout(ROWS, COLUMNS));
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                CellButton button = new CellButton();
                button.setActionCommand(i + "," + j);
                button.addActionListener(listener);
                buttons[i][j] = button;
                add(button);
            }
        }
    }

    public CellButton[][] getButtons() {
        return buttons;
    }
}
