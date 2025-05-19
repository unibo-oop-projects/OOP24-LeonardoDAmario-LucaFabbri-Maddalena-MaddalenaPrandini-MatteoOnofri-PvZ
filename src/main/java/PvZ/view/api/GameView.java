package PvZ.view.api;

import PvZ.controller.api.ViewListener;
import PvZ.utilities.GameEntity;


import java.util.Set;;

public interface GameView {
    void show();
    void close();

    boolean isVisible();

    void render(Set<GameEntity> entities, int suns, int kills);
    void setViewListener(ViewListener listener);
}
