package PvZ.view.api;

import PvZ.utilities.GameEntity;


import java.util.Set;;

public interface GameView {
    void show();
    void update();
    void close();

    boolean isVisible();

    void render(Set<GameEntity> entities, int suns, int kills);
}
