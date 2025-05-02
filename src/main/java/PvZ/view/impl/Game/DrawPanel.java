package PvZ.view.impl.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JPanel;
import PvZ.utilities.EntityType;
import PvZ.utilities.GameEntity;

public class DrawPanel extends JPanel {

    private static final int CELL_SIZE = 80; // o la tua vera cella
    private static final int LEFT_MARGIN = 0; // aggiorna se hai margini
    private static final int TOP_MARGIN = 0;  // aggiorna se hai margini

    private Set<GameEntity> movingEntities = new HashSet<>();

    public DrawPanel() {
        this.setOpaque(false); // trasparente
    }

    public void updateMovingEntities(Set<GameEntity> entities) {
        this.movingEntities = new HashSet<>(entities.stream()
            .filter(entity -> entity.type() == EntityType.BULLET || entity.type() == EntityType.ZOMBIE)
            .collect(Collectors.toSet()));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Disegna gli zombie e i proiettili sopra la griglia
        Graphics2D g2 = (Graphics2D) g.create();

        for (GameEntity e : movingEntities) {
            int pixelX = LEFT_MARGIN + (int) (e.position().x() * CELL_SIZE);
            int pixelY = TOP_MARGIN + (int) (e.position().y() * CELL_SIZE);

            if (e.type() == EntityType.ZOMBIE) {
                g2.setColor(Color.RED);
                g2.fillRect(pixelX, pixelY, CELL_SIZE, CELL_SIZE);
            } else if (e.type() == EntityType.BULLET) {
                g2.setColor(Color.BLUE);
                // centrato nella cella verticale, parte un po’ più avanti orizzontalmente
                int bulletSize = 20;
                int offsetY = (CELL_SIZE - bulletSize) / 2;
                g2.fillOval(pixelX, pixelY + offsetY, bulletSize, bulletSize);
            }
        }

        g2.dispose();
    }
}