package PvZ;

import PvZ.model.api.GameMenu.MenuModel;
import PvZ.model.impl.GameMenu.MenuModelImpl;
import PvZ.view.impl.Menu.MainGameFrame;
import PvZ.view.impl.Menu.MenuView;
import PvZ.controller.impl.Menu.MenuController;

import javax.swing.*;

public class PvZ {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGameFrame mainGameFrame = new MainGameFrame();
            MenuView menuView = new MenuView();
            MenuModel menuModel = new MenuModelImpl();
            MenuController menuController = new MenuController(menuModel, menuView, mainGameFrame);

            mainGameFrame.add(menuView);
            mainGameFrame.setVisible(true);
        });
    }
}


