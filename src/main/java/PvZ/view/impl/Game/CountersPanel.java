package PvZ.view.impl.Game;

import javax.swing.*;
import java.awt.*;

public class CountersPanel extends JPanel {
    private final JLabel sunCountLabel;
    private final JLabel killCountLabel;

    public CountersPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.sunCountLabel = new JLabel("Sun: 0");
        this.killCountLabel = new JLabel("Kills: 0");

        this.sunCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.killCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(20));
        this.add(sunCountLabel);
        this.add(Box.createVerticalStrut(20));
        this.add(killCountLabel);

        this.setPreferredSize(new Dimension(150, 200));
    }

    public void setSunCount(int sunCount) {
        SwingUtilities.invokeLater(() -> sunCountLabel.setText("Sun: " + sunCount));
    }

    public void setKillCount(int killCount) {
        SwingUtilities.invokeLater(() -> killCountLabel.setText("Kills: " + killCount));
    }
}
