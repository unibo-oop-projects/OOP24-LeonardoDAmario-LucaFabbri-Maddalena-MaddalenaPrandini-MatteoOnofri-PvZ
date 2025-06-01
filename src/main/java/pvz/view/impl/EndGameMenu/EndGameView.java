package pvz.view.impl.EndGameMenu;

import pvz.view.impl.Game.MainGameFrame;

import javax.swing.*;
import java.awt.*;

public class EndGameView extends JPanel {

    public final JButton backToMenuButton = new JButton("Torna al menu");
    public final JButton exitButton = new JButton("Esci");

    public EndGameView(boolean hasWon, MainGameFrame mainGameFrame) {
        this.setLayout(new BorderLayout());

        String messageText = hasWon ? "Hai vinto!" : "Hai perso!";
        JLabel message = new JLabel(messageText, SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.BOLD, 40));
        message.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        this.add(message, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backToMenuButton);
        buttonPanel.add(exitButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

    }

    public JButton getBackToMenuButton() {
        return backToMenuButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
