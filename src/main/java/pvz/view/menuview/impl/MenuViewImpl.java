package pvz.view.menuview.impl;

import pvz.model.game.api.Difficulty;
import pvz.utilities.Resolution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuViewImpl extends JPanel {

    private final JButton playButton;
    private final JButton difficultyButton;
    private final JButton tutorialButton;
    private final JButton exitButton;
    private final JLabel difficultyLabel;
    private final JComboBox<Resolution> resolutionCombo;

    public MenuViewImpl() {
        this.setLayout(new BorderLayout());


        JLabel titleLabel = new JLabel("Piante contro Zombie", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        this.add(titleLabel, BorderLayout.NORTH);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(2, 1, 5, 5));
        settingsPanel.setBackground(new Color(34, 139, 34));

        difficultyLabel = new JLabel("Difficoltà: Normale", SwingConstants.CENTER);
        difficultyLabel.setFont(new Font("Arial", Font.BOLD, 28));
        settingsPanel.add(difficultyLabel);

        resolutionCombo = new JComboBox<>(Resolution.values());
        resolutionCombo.setSelectedItem(Resolution.R800x600);
        resolutionCombo.setFont(new Font("Arial", Font.PLAIN, 18));
        JPanel resPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resPanel.setBackground(new Color(34, 139, 34));
        JLabel resLabel = new JLabel("Risoluzione: ", SwingConstants.CENTER);
        resLabel.setFont(new Font("Arial", Font.BOLD, 28));
        resPanel.add(resLabel);
        resPanel.add(resolutionCombo);
        settingsPanel.add(resPanel);
        this.add(settingsPanel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        playButton = new JButton("Gioca");
        difficultyButton = new JButton("Seleziona Difficoltà");
        tutorialButton = new JButton("Tutorial");
        exitButton = new JButton("Esci");

        buttonPanel.add(playButton);
        buttonPanel.add(difficultyButton);
        buttonPanel.add(tutorialButton);
        buttonPanel.add(exitButton);

        this.add(buttonPanel, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));

        //prova colori
        this.setBackground(new Color(34, 139, 34));
        buttonPanel.setBackground(new Color(34, 139, 34));

        //prova stili

        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        difficultyLabel.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));


        playButton.setFont(new Font("Arial", Font.PLAIN, 20));
        difficultyButton.setFont(new Font("Arial", Font.PLAIN, 20));
        tutorialButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));

    }

    public void updateDifficultyLabel(Difficulty difficulty) {
        this.difficultyLabel.setText("Difficoltà: " + difficulty.toString());
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getDifficultyButton() {
        return difficultyButton;
    }

    public JButton getTutorialButton() {
        return tutorialButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void addResolutionListener(ActionListener listener) {
        this.resolutionCombo.addActionListener(listener);
    }
    public Resolution getSelectedResolution() {
        return (Resolution) this.resolutionCombo.getSelectedItem();
    }

    public void setSelectedResolution(Resolution resolution) {
        this.resolutionCombo.setSelectedItem(resolution);
    }
}
