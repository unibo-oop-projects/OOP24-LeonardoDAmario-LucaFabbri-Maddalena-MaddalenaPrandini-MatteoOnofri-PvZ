package PvZ.view.impl.Game.grid;

import javax.swing.*;

public class CellButton extends JButton {
    public CellButton(int row, int col) {
        super();
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setActionCommand(row + "," + col);
    }
}
