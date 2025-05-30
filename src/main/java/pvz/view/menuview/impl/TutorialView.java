package pvz.view.menuview.impl;

import pvz.view.gameview.impl.MainGameFrame;

import javax.swing.*;
import java.awt.*;

public class TutorialView extends JPanel {
    public TutorialView(MainGameFrame frame) {
        this.setLayout(new BorderLayout());

        JTextArea text = new JTextArea();
        text.setText("""
                -Uccidi 20 Zombie per vincere!
                
                -Se uno Zombie arriva oltre le tue piante hai perso!
                
                -Per continuare a piantare piante ti servono i soli! \
                Assicurati di piantare sempre i girasoli per generare i tuoi soli!
                
                PIANTE DISPONIBILI:
                
                -Sunflower: Genera periodicamente soli per poter piazzare altre piante!
                
                -Peashooter: Spara semi agli zombie per proteggere il tuo giardino!
                
                -Wallnut: Una noce esplosiva che uccide istantaneamente il primo zombie che la tocca insieme a se stessa!"""
        );
        text.setEditable(false);
        text.setFont(new Font("Arial", Font.PLAIN, 18));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(text);
        this.add(scroll, BorderLayout.CENTER);

        JButton back = new JButton("Indietro");
        back.addActionListener(e -> frame.returnToMenu());
        this.add(back, BorderLayout.SOUTH);
    }
}
