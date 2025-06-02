package pvz.controller.gamecontroller.api;

import pvz.controller.maincontroller.api.Controller;
import pvz.model.game.api.Difficulty;
import pvz.utilities.Resolution;

public interface GameController extends Controller {

    void startGame(Difficulty difficulty, Resolution resolution);

    void stopGame();
    
}
