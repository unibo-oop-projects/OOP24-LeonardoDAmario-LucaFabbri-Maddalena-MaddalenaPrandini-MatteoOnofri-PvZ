package pvz.controller.api;

import pvz.utilities.Position;

public interface ViewListener {
    
    enum UserInputRoaster {
        PEASHOOTER, SUNFLOWER, WALLNUT
    }

    void processInputRoaster(UserInputRoaster input);
    void processInputGrid(Position position);

}
