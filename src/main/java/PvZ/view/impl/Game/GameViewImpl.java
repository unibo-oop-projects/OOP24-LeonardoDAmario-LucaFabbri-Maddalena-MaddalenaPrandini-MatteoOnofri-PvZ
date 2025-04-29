package PvZ.view.impl.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.*;
import PvZ.controller.api.ViewListener;
import PvZ.utilities.EntityType;
import PvZ.utilities.GameEntity;
import PvZ.utilities.Position;
import PvZ.view.api.GameView;
import PvZ.view.impl.Game.grid.CellButton;
import PvZ.view.impl.Game.grid.GridPanel;

public class GameViewImpl extends JFrame implements GameView, ActionListener {
    private final GridPanel grid;
    private final PlantRoaster roaster;
    private final CountersPanel countersPanel;
    private ViewListener viewListener;


    public GameViewImpl(){
        super("Plants vs Zombie - OOP24");

        this.grid = new GridPanel(this);
        this.roaster = new PlantRoaster();
        this.countersPanel = new CountersPanel();

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(roaster, BorderLayout.NORTH);

        mainPanel.add(grid, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(countersPanel, BorderLayout.WEST);

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void update() {
        SwingUtilities.invokeLater(() -> this.setVisible(true));
    }

    @Override
    public void close() {
        SwingUtilities.invokeLater(() -> this.setVisible(false));
    }


    @Override
    public void render(Set<GameEntity> entities, int suns, int kills) {
        for (GameEntity entity : entities) {
            if (entity.type() == EntityType.PEASHOOTER ||
                    entity.type() == EntityType.SUNFLOWER ||
                    entity.type() == EntityType.WALLNUT) {

                int row = (int) entity.position().x();
                int col = (int) entity.position().y();

                Color color = switch (entity.type()) {
                    case PEASHOOTER -> Color.GREEN;
                    case SUNFLOWER -> Color.YELLOW;
                    case WALLNUT -> new Color(139, 69, 19);
                    default -> null;
                };

                if (color != null) {
                    grid.colorCell(row, col, color);
                }
            }
        }

        countersPanel.setSunCount(suns);
        countersPanel.setKillCount(kills);
    }

    @Override
    public void setViewListener(ViewListener listener) {
        this.viewListener = listener;
        this.roaster.setViewListener(listener);
    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }
}