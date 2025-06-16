package pvz.view.gameview.impl;

import pvz.model.zombies.api.ZombieType;
import pvz.utilities.GameEntity;
import pvz.utilities.PlantType;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.EnumMap;
import java.util.Set;

/**
 * Custom JPanel responsible for drawing game entities such as plants, zombies, bullets, and lawn mowers.
 */
public class DrawPanel extends JPanel {

    private static final int DEFAULT_CELL_SIZE = 80;
    private static final int MARGIN_X = 20;
    private static final int MARGIN_Y = 20;
    private static final int BULLET_SIZE = 20;
    private static final int MOWER_HEIGHT_DIVIDER = 6;
    private static final Color PEASHOOTER_COLOR = new Color(34, 139, 34);
    private static final Color SUNFLOWER_COLOR = new Color(255, 215, 0);
    private static final Color WALLNUT_COLOR = new Color(139, 69, 19);
    private static final Color BASICZOMBIE_COLOR = new Color(255, 128, 128);
    private static final Color FASTZOMBIE_COLOR = new Color(255, 64, 64);
    private static final Color STRONGZOMBIE_COLOR = new Color(192, 0, 64);
    private static final Color BEASTZOMBIE_COLOR = new Color(128, 0, 128);

    private static int cellSize;
    private final double scaling;

    private Set<GameEntity> entities = Set.of();
    private final EnumMap<PlantType, Color> plantColors = new EnumMap<>(PlantType.class);
    private final EnumMap<ZombieType, Color> zombieColors = new EnumMap<>(ZombieType.class);

    /**
     * Constructs a new DrawPanel with the specified scaling factor.
     *
     * @param scaling the UI scaling factor based on resolution
     */
    public DrawPanel(final double scaling) {
        this.setOpaque(false);
        this.scaling = scaling;
        plantColors.put(PlantType.PEASHOOTER, PEASHOOTER_COLOR);
        plantColors.put(PlantType.SUNFLOWER, SUNFLOWER_COLOR);
        plantColors.put(PlantType.WALLNUT, WALLNUT_COLOR);
        zombieColors.put(ZombieType.BASICZOMBIE, BASICZOMBIE_COLOR);
        zombieColors.put(ZombieType.FASTZOMBIE, FASTZOMBIE_COLOR);
        zombieColors.put(ZombieType.STRONGZOMBIE, STRONGZOMBIE_COLOR);
        zombieColors.put(ZombieType.BEASTZOMBIE, BEASTZOMBIE_COLOR);
        double doubleCell = scaling * DEFAULT_CELL_SIZE;
        cellSize = (int) doubleCell;
    }

    /**
     * Updates the entities to be drawn and repaints the panel.
     *
     * @param entities the current set of game entities
     */
    public void updateMovingEntities(final Set<GameEntity> entities) {
        this.entities = entities;
        repaint();
    }

    /**
     * Paints the game entities onto the panel.
     *
     * @param g the Graphics object to protect
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        for (GameEntity e : entities) {
            int scalingX = (int) (MARGIN_X * scaling);
            int scalingY = (int) (MARGIN_Y * scaling);
            int plantX = scalingX + (int) (e.position().x() * cellSize);
            int plantY = scalingY + (int) (e.position().y() * cellSize);

            int pixelX = MARGIN_X + (int) (e.position().x() * cellSize);
            int pixelY = MARGIN_Y + (int) (e.position().y() * cellSize);

            switch (e.type()) {
                case PEASHOOTER, SUNFLOWER, WALLNUT -> {
                    PlantType plant = switch (e.type()) {
                        case PEASHOOTER -> PlantType.PEASHOOTER;
                        case SUNFLOWER -> PlantType.SUNFLOWER;
                        case WALLNUT -> PlantType.WALLNUT;
                        default -> null;
                    };
                    Color c = plantColors.getOrDefault(plant, Color.GREEN);
                    g2.setColor(c);
                    g2.fillRect(plantX, plantY, cellSize / 2, cellSize / 2);
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
                    g2.fillRect(pixelX - MARGIN_X, pixelY - MARGIN_Y, cellSize / 2, cellSize);
                }
                case BULLET -> {
                    g2.setColor(Color.BLUE);
                    int offsetY = (cellSize - BULLET_SIZE) / 2;
                    g2.fillOval(pixelX - MARGIN_X, pixelY + offsetY - MARGIN_Y, BULLET_SIZE, BULLET_SIZE);
                }
                case LAWNMOWER -> {
                    g2.setColor(Color.GRAY);
                    int mowerHeight = cellSize / MOWER_HEIGHT_DIVIDER;
                    int offset = cellSize - mowerHeight;
                    g2.fillRect(pixelX - MARGIN_X,
                            (int) (e.position().y() * cellSize) + offset + (int) e.position().y(),
                            cellSize, mowerHeight);
                }
                default -> { }
            }
        }
        g2.dispose();
    }
}
