package pvz.view.menuview.impl;

import pvz.controller.menucontroller.api.MenuController;
import pvz.model.game.api.Difficulty;
import pvz.utilities.Resolution;

import javax.swing.*;
import java.awt.*;

public class MenuViewImpl extends JPanel {

    private final JButton playButton;
    private final JButton difficultyButton;
    private final JButton tutorialButton;
    private final JButton exitButton;
    private final JLabel difficultyLabel;
    private final JComboBox<Resolution> resolutionCombo;
    private Difficulty currentDifficulty = Difficulty.NORMAL;
    private Resolution currentResolution = Resolution.R800x600;
    private final MenuController parentController;
    private final JFrame frame = new JFrame();

    public MenuViewImpl(MenuController controller) {

        this.parentController = controller;
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

        initListeners();
        configureFrame();
    }

    private void configureFrame() {
        frame.setTitle("Piante contro Zombie");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(this.currentResolution.getWidth(), this.currentResolution.getHeight());
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void initListeners() {
        this.playButton.addActionListener(e -> {
            parentController.startGame(currentDifficulty, currentResolution);
        });

        this.difficultyButton.addActionListener(e -> {
            currentDifficulty = switch (currentDifficulty) {
                case NORMAL -> Difficulty.HARD;
                case HARD -> Difficulty.EASY;
                case EASY -> Difficulty.NORMAL;
            };
            updateDifficultyLabel(currentDifficulty);
        });

        this.tutorialButton.addActionListener(e -> parentController.showTutorialView(currentResolution));

        this.exitButton.addActionListener(e -> System.exit(0));

        this.resolutionCombo.addActionListener(e -> {
            Resolution sel = (Resolution) resolutionCombo.getSelectedItem();
            setResolution(sel);
        });

    }

    public void dispose() {
        frame.dispose();
    }

    public void setResolution(Resolution resolution) {
        Resolution previousResolution = this.currentResolution;
        this.currentResolution = resolution;

        frame.setSize(resolution.getWidth(), resolution.getHeight());

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Vuoi mantenere la nuova risoluzione?",
                "Conferma risoluzione",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.NO_OPTION) {
            frame.setSize(previousResolution.getWidth(), previousResolution.getHeight());
            this.setSelectedResolution(previousResolution);
        }
    }


    public void updateDifficultyLabel(Difficulty difficulty) {
        this.difficultyLabel.setText("Difficoltà: " + difficulty.toString());
    }

    public void setSelectedResolution(Resolution resolution) {
        this.resolutionCombo.setSelectedItem(resolution);
    }
}