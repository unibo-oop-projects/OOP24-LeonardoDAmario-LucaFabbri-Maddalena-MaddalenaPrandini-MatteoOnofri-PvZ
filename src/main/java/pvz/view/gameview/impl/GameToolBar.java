package pvz.view.gameview.impl;

import pvz.controller.gamecontroller.api.ViewListener;

import javax.swing.*;
import java.awt.*;

/**
 * Toolbar component displaying plant selection buttons and game statistics (sun and kill count).
 */
public class GameToolBar extends JPanel {

    private final JButton peaButton = new JButton("Peashooter (50)");
    private final JButton snflButton = new JButton("Sunflower (25)");
    private final JButton wlButton = new JButton("Wall-nut (75)");
    private final JLabel sunCounterLabel = new JLabel("☀ Sun: 0");
    private final JLabel killCounterLabel = new JLabel("💀 Kills: 0");
    private ViewListener listener;

    /**
     * Constructs the toolbar and initializes UI components.
     */
    public GameToolBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.LIGHT_GRAY);


        add(peaButton);
        add(snflButton);
        add(wlButton);
        add(Box.createHorizontalStrut(20));
        add(sunCounterLabel);
        add(killCounterLabel);

        peaButton.addActionListener(e -> PlantSelection(ViewListener.UserInputRoaster.PEASHOOTER));
        snflButton.addActionListener(e -> PlantSelection(ViewListener.UserInputRoaster.SUNFLOWER));
        wlButton.addActionListener(e -> PlantSelection(ViewListener.UserInputRoaster.WALLNUT));
    }

    /**
     * Sets the listener for handling plant selection inputs.
     *
     * @param listener the ViewListener instance
     */
    public void setViewListener(ViewListener listener) {
        this.listener = listener;
    }

    /**
     * Notifies the listener of the selected plant type.
     *
     * @param input the selected plant input
     */
    private void PlantSelection(ViewListener.UserInputRoaster input) {
        if (listener != null) {
            listener.processInputRoaster(input);
        }
    }

    /**
     * Updates the sun and kill counters in the UI.
     *
     * @param sunCount  the current sun (currency) amount
     * @param killCount the number of zombies killed
     */
    public void updateStats(int sunCount, int killCount) {
        sunCounterLabel.setText("☀ Sun: " + sunCount);
        killCounterLabel.setText("💀 Kills: " + killCount);
    }


}