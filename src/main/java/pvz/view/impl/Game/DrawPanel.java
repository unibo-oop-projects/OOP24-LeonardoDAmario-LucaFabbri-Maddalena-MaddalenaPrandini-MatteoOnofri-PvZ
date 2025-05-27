package pvz.view.impl.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.EnumMap;
import java.util.Set;
import javax.swing.JPanel;

import pvz.model.api.plants.PlantType;
import pvz.utilities.GameEntity;

public class DrawPanel extends JPanel {

    private static int cell_size;
    private static final int MARGIN_X = 20; //margine dal bordo sinistro
    private static final int MARGIN_Y = 20;  // margine dal bordo superiore

    private Set<GameEntity> entities = Set.of();
    private final EnumMap<PlantType, Color> plantcolors = new EnumMap<>(PlantType.class);

    public DrawPanel(double scaling) {
        this.setOpaque(false);// trasparente
        plantcolors.put(PlantType.PEASHOOTER, new Color(34, 139, 34));
        plantcolors.put(PlantType.SUNFLOWER, new Color(255, 215, 0));
        plantcolors.put(PlantType.WALLNUT, new Color(139, 69, 19));
        scaling = scaling * 80;
        cell_size = (int)scaling;
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
                    g2.fillRect(pixelX, pixelY, cell_size/2, cell_size/2);
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
                default -> {}
            }
        }
        g2.dispose();
    }
}