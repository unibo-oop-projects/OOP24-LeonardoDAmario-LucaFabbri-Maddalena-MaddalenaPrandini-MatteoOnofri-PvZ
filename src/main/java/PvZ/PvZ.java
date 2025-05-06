package PvZ;

import PvZ.model.api.GameModel;
import PvZ.model.api.GameMenu.MenuModel;
import PvZ.model.impl.GameModelImpl;
import PvZ.model.impl.GameMenu.MenuModelImpl;
import PvZ.view.api.GameView;
import PvZ.view.impl.Game.GameViewImpl;
import PvZ.view.impl.Game.MainGameFrame;
import PvZ.view.impl.Menu.MenuView;
import PvZ.controller.api.GameController;
import PvZ.controller.api.ViewListener;
import PvZ.controller.impl.GameControllerImpl;
import PvZ.controller.impl.Menu.MenuController;

import javax.swing.*;

public class PvZ{
    public static void main(String[] args) {
        GameModel model = new GameModelImpl();

        GameView view = new GameViewImpl();

        GameController controller = new GameControllerImpl(model, view);

        view.setViewListener((ViewListener) controller);

        controller.startGame();
    }
}



