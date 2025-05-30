package pvz.controller.gamecontroller.api;

import pvz.model.game.api.Difficulty;
import pvz.utilities.Resolution;

public interface GameController {

    void startGame(Difficulty difficulty, Resolution resolution);

    void stopGame();
    
}
