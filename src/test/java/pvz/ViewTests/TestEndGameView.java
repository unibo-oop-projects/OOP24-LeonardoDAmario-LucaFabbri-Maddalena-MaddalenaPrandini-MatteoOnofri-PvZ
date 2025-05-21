package pvz.ViewTests;

import pvz.view.impl.EndGameMenu.EndGameView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TestEndGameView {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Fine Gioco");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);

            // Cambia questo valore per testare vittoria o sconfitta
            boolean hasWon = true;
            EndGameView endView = new EndGameView(hasWon);

            endView.backToMenuButton.addActionListener((ActionEvent e) -> {
                JOptionPane.showMessageDialog(frame, "Tornare al menu...");
            });

            endView.exitButton.addActionListener((ActionEvent e) -> {
                System.exit(0);
            });

            frame.setContentPane(endView);
            frame.setVisible(true);
        });
    }
}
