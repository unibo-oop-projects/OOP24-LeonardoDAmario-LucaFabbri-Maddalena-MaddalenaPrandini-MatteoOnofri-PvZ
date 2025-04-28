package PvZ.view.impl.Plants;

import PvZ.utilities.EntityType;

import javax.swing.*;
import java.awt.*;

public class CellButton extends JButton {
    private EntityType entity;

    public CellButton() {
        this.setBackground(Color.LIGHT_GRAY);
        this.setOpaque(true);
        this.setBorderPainted(true);
        this.entity = null;
    }

    public  void setEntity(EntityType entity) {
        this.entity = entity;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(entity != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(EntityColor(entity));
            int size = Math.min(getWidth(), getHeight())/2;
            int x = (getWidth() - size)/2;
            int y = (getHeight() - size)/2;
            g2d.fillRect(x, y, size, size);
            g2d.dispose();
        }
    }

    private Color EntityColor(EntityType entity) {
        return switch (entity){
            case PEASHOOTER -> Color.GREEN;
            case SUNFLOWER -> Color.YELLOW;
            case WALLNUT -> new Color(139, 69, 19);
            case ZOMBIE -> Color.GRAY;
            case BULLET -> Color.BLACK;
        };
    }
}
