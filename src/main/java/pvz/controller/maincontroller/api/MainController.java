package pvz.controller.maincontroller.api;

import pvz.model.game.api.Difficulty;
import pvz.utilities.Resolution;

public interface MainController extends Controller {

    void start();

    void startGame(Difficulty difficulty, Resolution resolution);

    void goToMenu();

    void goToEndGame(boolean hasWon);
}
