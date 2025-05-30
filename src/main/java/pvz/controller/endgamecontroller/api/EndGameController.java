package pvz.controller.endgamecontroller.api;

import pvz.controller.maincontroller.api.Controller;

public interface EndGameController extends Controller {

    void openEndGameMenu(boolean hasWon);

    void closeEndGameMenu();
}
