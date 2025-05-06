package PvZ.view.impl.Game;

import PvZ.model.api.Plants.PlantType;
import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class GameToolBar extends JPanel {

    private final JLabel sunLabel = new JLabel("Sun: 0");
    private final JLabel killLabel = new JLabel("Kills: 0");

    public GameToolBar(Consumer<PlantType> onSelect) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton pea = new JButton("Peashooter");
        JButton sun = new JButton("Sunflower");
        JButton wall = new JButton("Wall-nut");
        pea.addActionListener(e -> onSelect.accept(PlantType.PEASHOOTER));
        sun.addActionListener(e -> onSelect.accept(PlantType.SUNFLOWER));
        wall.addActionListener(e -> onSelect.accept(PlantType.WALLNUT));
        add(pea); add(sun); add(wall);
        add(sunLabel); add(killLabel);
    }

    public void statesUpdate(int sun, int kill) {
        sunLabel.setText("Sun: " + sun);
        killLabel.setText("Kills: " + kill);
    }
}