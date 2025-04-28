package PvZ.view.impl;

import java.awt.BorderLayout;
import java.util.Set;
import javax.swing.*;
import PvZ.controller.api.ViewListener;
import PvZ.utilities.GameEntity;
import PvZ.view.api.GameView;
import PvZ.view.impl.Plants.Grid;
import PvZ.view.impl.Plants.PlantRoaster;

public class GamePanel extends JFrame implements GameView{
    private final Grid grid;
    private final PlantRoaster roaster;

    public GamePanel(ViewListener listener){
        setTitle("Plants vs Zombies");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        grid = new Grid(listener);
        roaster = new PlantRoaster(listener);

        add(grid, BorderLayout.CENTER);
        add(roaster, BorderLayout.NORTH);

    }

    @Override
    public void update() {

    }

    @Override
    public void close() {

    }

    @Override
    public void render(Set<GameEntity> entities, int suns, int kills) {

    }


}