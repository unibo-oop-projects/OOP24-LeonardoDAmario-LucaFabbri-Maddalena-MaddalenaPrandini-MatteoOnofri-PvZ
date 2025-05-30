package pvz;

import pvz.view.gameview.impl.MainGameFrame;

import javax.swing.*;

public class PvZ {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGameFrame::launchGame);
    }
}
