package PvZ;

import PvZ.model.api.GameMenu.MenuModel;
import PvZ.model.impl.GameMenu.MenuModelImpl;
import PvZ.view.impl.MainGameFrame;
import PvZ.view.impl.Menu.MenuView;
import PvZ.controller.impl.Menu.MenuController;

import javax.swing.*;

public class PvZ{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGameFrame frame = new MainGameFrame();
            MenuModel model = new MenuModelImpl();
            MenuView view = new MenuView();
            MenuController controller = new MenuController(model, view, frame);

            frame.addMenuView(view);
            frame.setVisible(true);
        });
    }
}



