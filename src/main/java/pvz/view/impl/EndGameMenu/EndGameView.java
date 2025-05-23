package pvz.view.impl.EndGameMenu;

import pvz.PvZ;

import javax.swing.*;
import java.awt.*;

public class EndGameView extends JPanel {
    public final JFrame frame = new JFrame();
    public final JButton backToMenuButton = new JButton("Torna al menu");
    public final JButton exitButton = new JButton("Esci");

    public EndGameView(boolean hasWon) {
        setLayout(new BorderLayout());

        String messageText = hasWon ? "Hai vinto!" : "Hai perso!";
        JLabel message = new JLabel(messageText, SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.BOLD, 40));
        add(message, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backToMenuButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        exitButton.addActionListener(e -> System.exit(0));
        backToMenuButton.addActionListener(e -> {
                    frame.dispose();
                    PvZ.startGame();
                }
        );
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }
}