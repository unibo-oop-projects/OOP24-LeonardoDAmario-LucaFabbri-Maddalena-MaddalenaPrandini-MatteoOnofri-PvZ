package pvz.view.endgameview.impl;

import pvz.controller.endgamecontroller.api.EndGameController;
import pvz.view.endgameview.api.EndGameView;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

/**
 * Implementation of the EndGameView interface.
 * Displays the end-game screen with victory or defeat and navigation buttons.
 */
public final class EndGameViewImpl extends JPanel implements EndGameView {

    /** Button to return to the main menu. */
    private final JButton backToMenuButton = new JButton("Torna al menu");

    /** Button to exit the application. */
    private final JButton exitButton = new JButton("Esci");

    /** The controller for end-game logic. */
    private final EndGameController parentController;

    /** The frame hosting this view. */
    private final JFrame frame = new JFrame();

    private static final int MESSAGE_FONT_SIZE = 40;
    private static final int MESSAGE_TOP_BOTTOM_PADDING = 50;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    /**
     * Constructs the end-game view.
     *
     * @param controller the EndGameController instance
     * @param hasWon true if the player has won, false otherwise
     */
    public EndGameViewImpl(final EndGameController controller, final boolean hasWon) {
        this.parentController = controller;
        this.setLayout(new BorderLayout());

        String messageText = hasWon ? "Hai vinto!" : "Hai perso!";
        JLabel message = new JLabel(messageText, SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.BOLD, MESSAGE_FONT_SIZE));
        message.setBorder(BorderFactory.createEmptyBorder(
                MESSAGE_TOP_BOTTOM_PADDING, 0, MESSAGE_TOP_BOTTOM_PADDING, 0));
        this.add(message, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backToMenuButton);
        buttonPanel.add(exitButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

        configureFrame();
        initActionListeners();
    }

    /**
     * Closes the end-game window.
     */
    public void close() {
        this.frame.dispose();
    }

    /**
     * Configures the main frame of the end-game screen.
     */
    private void configureFrame() {
        frame.setTitle("Piante contro Zombie");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Initializes the action listeners for the buttons.
     */
    private void initActionListeners() {
        backToMenuButton.addActionListener(e -> parentController.closeEndGameMenu());
        exitButton.addActionListener(e -> System.exit(0));
    }

}
