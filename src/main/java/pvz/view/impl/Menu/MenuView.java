package pvz.view.impl.Menu;

import pvz.model.api.Difficulty;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel {

    private final JButton playButton;
    private final JButton difficultyButton;
    private final JButton exitButton;
    private final JLabel difficultyLabel;

    public MenuView() {
        this.setLayout(new BorderLayout());


        JLabel titleLabel = new JLabel("Piante contro Zombie", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        this.add(titleLabel, BorderLayout.NORTH);

        difficultyLabel = new JLabel("Difficoltà: Normale", SwingConstants.CENTER);
        difficultyLabel.setFont(new Font("Arial", Font.BOLD, 28));
        this.add(difficultyLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        playButton = new JButton("Gioca");
        difficultyButton = new JButton("Seleziona Difficoltà");
        exitButton = new JButton("Esci");

        buttonPanel.add(playButton);
        buttonPanel.add(difficultyButton);
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

    public JButton getExitButton() {
        return exitButton;
    }
}
