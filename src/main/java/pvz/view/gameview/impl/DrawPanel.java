package pvz.view.gameview.impl;


import pvz.utilities.GameEntity;
import pvz.utilities.PlantType;
import pvz.model.zombies.api.ZombieType;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Set;

/**
 * Custom JPanel responsible for drawing game entities such as plants, zombies, bullets, and lawn mowers.
 */

public class DrawPanel extends JPanel {

    private static int cell_size;
    private static final int MARGIN_X = 20; //margine dal bordo sinistro
    private static final int MARGIN_Y = 20;  // margine dal bordo superiore
    private double scaling;

    private Set<GameEntity> entities = Set.of();
    private final EnumMap<PlantType, Color> plantcolors = new EnumMap<>(PlantType.class);
    private final EnumMap<ZombieType, Color> zombieColors = new EnumMap<>(ZombieType.class);

    /**
     * Constructs a new DrawPanel with the specified scaling factor.
     *
     * @param scaling the UI scaling factor based on resolution
     */
    public DrawPanel(double scaling) {
        this.setOpaque(false);// trasparente
        this.scaling = scaling;
        plantcolors.put(PlantType.PEASHOOTER, new Color(34, 139, 34));
        plantcolors.put(PlantType.SUNFLOWER, new Color(255, 215, 0));
        plantcolors.put(PlantType.WALLNUT, new Color(139, 69, 19));
        zombieColors.put(ZombieType.BASICZOMBIE, new Color(255, 128, 128));
        zombieColors.put(ZombieType.FASTZOMBIE, new Color(255, 64, 64));     
        zombieColors.put(ZombieType.STRONGZOMBIE, new Color(192, 0, 64));   
        zombieColors.put(ZombieType.BEASTZOMBIE, new Color(128, 0, 128));
        double doubleCell = scaling * 80;
        cell_size = (int)doubleCell;
    }

    /**
     * Updates the entities to be drawn and repaints the panel.
     *
     * @param entities the current set of game entities
     */
    public void updateMovingEntities(Set<GameEntity> entities) {
        this.entities = entities;
        repaint();
    }

    /**
     * Paints the game entities onto the panel.
     *
     * @param g the Graphics object to protect
     */
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
                case BASICZOMBIE, STRONGZOMBIE, FASTZOMBIE, BEASTZOMBIE -> {
                    ZombieType zombie = switch (e.type()) {
                        case BASICZOMBIE -> ZombieType.BASICZOMBIE;
                        case FASTZOMBIE -> ZombieType.FASTZOMBIE;
                        case STRONGZOMBIE -> ZombieType.STRONGZOMBIE;
                        case BEASTZOMBIE -> ZombieType.BEASTZOMBIE;
                        default -> null;
                    };
                    Color c = zombieColors.getOrDefault(zombie, Color.GREEN);
                    g2.setColor(c);
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