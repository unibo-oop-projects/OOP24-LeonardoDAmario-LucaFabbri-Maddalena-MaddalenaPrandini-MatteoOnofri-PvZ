package PvZ.controller.api;

import PvZ.utilities.Position;

public interface ViewListener {
    
    enum UserInputRoaster {
        PEASHOOTER, SUNFLOWER, WALLNUT
    }

    void processInputRoaster(UserInputRoaster input);
    void processInputView(boolean isVisible);
    void processInputGrid(Position position);

}
