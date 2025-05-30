package pvz.view.gameview.api;

import pvz.controller.gamecontroller.api.ViewListener;
import pvz.model.entities.api.GameEntity;


import java.util.Set;;

public interface GameView {
    void show();
    void close();

    boolean isVisible();

    void render(Set<GameEntity> entities, int suns, int kills);
    void setViewListener(ViewListener listener);
}
