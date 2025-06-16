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
    private final double scaling;

    /**
     * Constructs the toolbar and initializes UI components.
     *
     * @param scaling the scaling factor for UI elements
     */
    public GameToolBar(final double scaling) {
        this.scaling = scaling;

        setLayout(new FlowLayout(FlowLayout.LEFT, (int) (10 * scaling), (int) (5 * scaling)));
        setBackground(Color.LIGHT_GRAY);

        Font font = new Font("SansSerif", Font.PLAIN, (int) (14 * scaling));
        peaButton.setFont(font);
        snflButton.setFont(font);
        wlButton.setFont(font);
        sunCounterLabel.setFont(font);
        killCounterLabel.setFont(font);

        int buttonWidth = (int) (140 * scaling);
        int buttonHeight = (int) (30 * scaling);

        peaButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        snflButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        wlButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        add(peaButton);
        add(snflButton);
        add(wlButton);
        add(Box.createHorizontalStrut((int) (20 * scaling)));
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
    public void setViewListener(final ViewListener listener) {
        this.listener = listener;
    }

    /**
     * Notifies the listener of the selected plant type.
     *
     * @param input the selected plant input
     */
    private void PlantSelection(final ViewListener.UserInputRoaster input) {
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
    public void updateStats(final int sunCount, final int killCount) {
        sunCounterLabel.setText("☀ Sun: " + sunCount);
        killCounterLabel.setText("💀 Kills: " + killCount);
    }
}
