package pvz.view.menuview.impl;

import pvz.utilities.Resolution;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Font;

/**
 * A simple JFrame that shows the game tutorial to the user.
 */
public class TutorialView extends JFrame {

    private static final int TEXT_FONT_SIZE = 18;

    /**
     * Constructs the TutorialView window.
     *
     * @param resolution the Resolution used to set the window size
     */
    public TutorialView(final Resolution resolution) {
        this.setLayout(new BorderLayout());

        JTextArea text = new JTextArea();
        text.setText("""
                - Uccidi 20 Zombie per vincere!

                - Se uno Zombie arriva oltre le tue piante hai perso!

                - Per continuare a piantare piante ti servono i soli!
                Assicurati di piantare sempre i girasoli per generare i tuoi soli!

                PIANTE DISPONIBILI:

                - Sunflower: Genera periodicamente soli per poter piazzare altre piante!

                - Peashooter: Spara semi agli zombie per proteggere il tuo giardino!

                - Wallnut: Una noce esplosiva che uccide istantaneamente il primo zombie che la tocca insieme a se stessa!
                """);
        text.setEditable(false);
        text.setFont(new Font("Arial", Font.PLAIN, TEXT_FONT_SIZE));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(text);
        this.add(scroll, BorderLayout.CENTER);

        JButton back = new JButton("Indietro");
        back.addActionListener(e -> this.dispose());
        this.add(back, BorderLayout.SOUTH);

        this.setSize(resolution.getWidth(), resolution.getHeight());
        this.setVisible(true);
    }
}
