package pvz.view.endgameview.impl;


import pvz.controller.endgamecontroller.api.EndGameController;
import pvz.utilities.Resolution;
import pvz.view.endgameview.api.EndGameView;

import javax.swing.*;
import java.awt.*;

public class EndGameViewImpl extends JPanel implements EndGameView {

    public final JButton backToMenuButton = new JButton("Torna al menu");
    public final JButton exitButton = new JButton("Esci");
    public final EndGameController parentController;
    private final JFrame frame = new JFrame();

    public EndGameViewImpl(EndGameController controller, boolean hasWon) {
        this.parentController = controller;
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

        configureFrame();
        initActionListeners();

    }

    public void close() {
        this.frame.dispose();
    }

    private void configureFrame() {

        frame.setTitle("Piante contro Zombie");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void initActionListeners() {
        backToMenuButton.addActionListener(e -> parentController.closeEndGameMenu());
        exitButton.addActionListener(e -> System.exit(0));
    }

}