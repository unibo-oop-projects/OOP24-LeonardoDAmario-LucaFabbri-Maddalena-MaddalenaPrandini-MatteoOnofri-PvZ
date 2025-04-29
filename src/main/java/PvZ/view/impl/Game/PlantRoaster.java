package PvZ.view.impl.Game;

import PvZ.controller.api.ViewListener;
import PvZ.utilities.Position;

import javax.swing.*;
import java.awt.*;

public class PlantRoaster extends JPanel {

    private static final int ROWS = 1;
    private static final int COLUMNS = 3;
    private ViewListener viewListener;

    public PlantRoaster(ViewListener listener) {
        setLayout(new GridLayout(ROWS, COLUMNS));

        JButton peashooter = new JButton("Peashooter");
        JButton sunflower = new JButton("Sunflower");
        JButton wallnut = new JButton("Wallnut");

        peashooter.addActionListener(e -> listener.processInputRoaster(ViewListener.UserInputRoaster.PEASHOOTER));
        sunflower.addActionListener(e -> listener.processInputRoaster(ViewListener.UserInputRoaster.SUNFLOWER));
        wallnut.addActionListener(e -> listener.processInputRoaster(ViewListener.UserInputRoaster.WALLNUT));

        add(peashooter);
        add(sunflower);
        add(wallnut);
    }
}
