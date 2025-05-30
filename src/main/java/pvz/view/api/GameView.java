package pvz.view.api;

import pvz.controller.gamecontroller.api.ViewListener;
import pvz.utilities.GameEntity;


import java.util.Set;;

public interface GameView {
    void show();
    void close();

    boolean isVisible();

    void render(Set<GameEntity> entities, int suns, int kills);
    void setViewListener(ViewListener listener);
}
