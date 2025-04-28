package PvZ.view.impl.Game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.*;
import PvZ.controller.api.ViewListener;
import PvZ.utilities.GameEntity;
import PvZ.utilities.Position;
import PvZ.view.api.GameView;
import PvZ.view.impl.Plants.Grid;
import PvZ.view.impl.Plants.PlantRoaster;

public class GamePanel extends JFrame implements GameView, ActionListener {
    private final Grid grid;
    private final PlantRoaster roaster;
    private final CountersPanel countersPanel;

    private ViewListener viewListener;

    public GamePanel(){
        super("Plants vs Zombie - OOP24");

        this.grid = new Grid(this);
        this.roaster = new PlantRoaster(viewListener);
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
        SwingUtilities.invokeLater(() -> this.setVisible(true));
    }

    @Override
    public void render(Set<GameEntity> entities, int suns, int kills) {
        resetGrid();
        entities.stream().forEach(entity -> {
            /**/
        });
        countersPanel.setKillCount(kills);
        countersPanel.setSunCount(suns);
    }

    private void resetGrid() {
        for (var row: grid.getButtons()){
            for (var cell: row){
                cell.setEntity(null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}