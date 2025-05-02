package PvZ;

import PvZ.controller.api.GameController;
import PvZ.controller.api.ViewListener;
import PvZ.controller.impl.GameControllerImpl;
import PvZ.model.api.GameMenu.MenuModel;
import PvZ.model.api.GameModel;
import PvZ.model.impl.GameMenu.MenuModelImpl;
import PvZ.model.impl.GameModelImpl;
import PvZ.view.impl.Game.GameViewImpl;
import PvZ.view.impl.Game.MainGameFrame;
import PvZ.view.impl.Menu.MenuView;
import PvZ.controller.impl.Menu.MenuController;

import javax.swing.*;

public class PvZ{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGameFrame mainGameFrame = new MainGameFrame();
            MenuView menuView = new MenuView();
            mainGameFrame.add(menuView);
            mainGameFrame.setVisible(true);
        });
    }
}



