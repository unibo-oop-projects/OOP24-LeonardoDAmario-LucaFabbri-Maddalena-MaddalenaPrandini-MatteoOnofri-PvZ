package pvz.view.gameview.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.EnumMap;
import java.util.Set;
import javax.swing.JPanel;

import pvz.model.plants.api.PlantType;
import pvz.model.entities.api.GameEntity;

public class DrawPanel extends JPanel {

    private static int cell_size;
    private static final int MARGIN_X = 20; //margine dal bordo sinistro
    private static final int MARGIN_Y = 20;  // margine dal bordo superiore
    private double scaling;

    private Set<GameEntity> entities = Set.of();
    private final EnumMap<PlantType, Color> plantcolors = new EnumMap<>(PlantType.class);

    public DrawPanel(double scaling) {
        this.setOpaque(false);// trasparente
        this.scaling = scaling;
        plantcolors.put(PlantType.PEASHOOTER, new Color(34, 139, 34));
        plantcolors.put(PlantType.SUNFLOWER, new Color(255, 215, 0));
        plantcolors.put(PlantType.WALLNUT, new Color(139, 69, 19));
        double doubleCell = scaling * 80;
        cell_size = (int)doubleCell;
    }

    public void updateMovingEntities(Set<GameEntity> entities) {
        this.entities = entities;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        for (GameEntity e : entities) {
            int scalingX = (int) ((int)MARGIN_X *scaling);
            int scalingY = (int) ((int)MARGIN_Y *scaling);
            int plantX = scalingX + (int) (e.position().x() * cell_size);
            int plantY = scalingY + (int) (e.position().y() * cell_size);

            int pixelX = MARGIN_X + (int) (e.position().x() * cell_size);
            int pixelY = MARGIN_Y + (int) (e.position().y() * cell_size);

            switch (e.type()){
                case PEASHOOTER, SUNFLOWER, WALLNUT -> {
                    PlantType plant = switch (e.type()){
                        case PEASHOOTER -> PlantType.PEASHOOTER;
                        case SUNFLOWER -> PlantType.SUNFLOWER;
                        case WALLNUT -> PlantType.WALLNUT;
                        default -> null;
                    };
                    Color c = plantcolors.getOrDefault(plant, Color.GREEN);
                    g2.setColor(c);
                    g2.fillRect(plantX, plantY, cell_size/2, cell_size/2);
                }
                case ZOMBIE -> {
                    g2.setColor(Color.RED);
                    g2.fillRect(pixelX-MARGIN_X, pixelY-MARGIN_Y, cell_size/2, cell_size);
                }
                case BULLET ->{
                    g2.setColor(Color.BLUE);
                    int bulletSize = 20;
                    int offsetY = (cell_size-bulletSize)/2;
                    g2.fillOval(pixelX-MARGIN_X, pixelY+offsetY-MARGIN_Y, bulletSize, bulletSize);
                }
                case LAWNMOWER -> {
                    g2.setColor(Color.GRAY);
                    int mowerHeight = cell_size/6;
                    int offset = (cell_size-mowerHeight);
                    g2.fillRect(pixelX-MARGIN_X, (int) (e.position().y() * cell_size) + offset + (int)e.position().y(), cell_size, mowerHeight);
                }

                default -> {}
            }
        }
        g2.dispose();
    }
}