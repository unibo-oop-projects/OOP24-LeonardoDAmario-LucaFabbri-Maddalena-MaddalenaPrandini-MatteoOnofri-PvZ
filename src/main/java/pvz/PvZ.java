package pvz;

import pvz.view.impl.Game.MainGameFrame;

import javax.swing.*;

public class PvZ {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGameFrame::launchGame);
    }
}
