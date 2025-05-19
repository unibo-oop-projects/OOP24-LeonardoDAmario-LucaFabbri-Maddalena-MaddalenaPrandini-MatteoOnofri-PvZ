package pvz;

import pvz.model.api.GameMenu.MenuModel;
import pvz.model.impl.GameMenu.MenuModelImpl;
import pvz.view.impl.Menu.MainGameFrame;
import pvz.view.impl.Menu.MenuView;
import pvz.controller.impl.Menu.MenuController;

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


