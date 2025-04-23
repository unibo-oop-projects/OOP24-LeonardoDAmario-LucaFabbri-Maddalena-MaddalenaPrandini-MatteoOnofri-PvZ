package PvZ.view.api;

import PvZ.model.api.GameModel;

public interface GameView {
    void show();
    void update();
    void close();

    boolean isVisible();

    void render(GameModel model);
}
