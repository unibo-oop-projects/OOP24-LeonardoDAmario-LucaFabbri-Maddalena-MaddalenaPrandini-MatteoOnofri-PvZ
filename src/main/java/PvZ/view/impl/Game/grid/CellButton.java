package PvZ.view.impl.Game.grid;

import javax.swing.*;
import java.awt.*;

public class CellButton extends JButton {
    private Color innerColor = null;

    public CellButton(int row, int col) {
        super();
        this.setActionCommand(row + "," + col);
        this.setFocusPainted(false);
        this.setBorderPainted(true);
        this.setContentAreaFilled(true);
        this.setOpaque(true);
    }

    public void setInnerColor(Color color) {
        this.innerColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (innerColor != null) {
            g.setColor(innerColor);
            int margin = 20;
            g.fillRect(margin, margin, getWidth() - 2 * margin, getHeight() - 2 * margin);
        }
    }
}
