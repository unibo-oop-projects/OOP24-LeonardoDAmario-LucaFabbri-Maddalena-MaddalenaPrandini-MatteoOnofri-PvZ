package pvz.controller.menucontroller.api;

import pvz.controller.maincontroller.api.Controller;
import pvz.model.game.api.Difficulty;
import pvz.utilities.Resolution;

public interface MenuController extends Controller {

    void openMenu();

    void closeMenu();

    void startGame(Difficulty difficulty, Resolution resolution);

    void showTutorialView(Resolution currentResolution);
}
