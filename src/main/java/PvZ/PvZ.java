package PvZ;

import PvZ.controller.api.GameController;
import PvZ.controller.impl.GameControllerImpl;
import PvZ.model.api.GameModel;
import PvZ.model.impl.GameModelImpl;
import PvZ.view.api.GameView;
import PvZ.view.impl.GameViewImpl;

public class Main {
    public static void main(String[] args) {
        GameModel model = new GameModelImpl();
        GameView view = new GameViewImpl();
        GameController controller = new GameControllerImpl(model, view);

        controller.startGame();
    }
}
