package PvZ.view.api;

import PvZ.model.api.GameModel;
import PvZ.model.api.Plants.PlantType;
import PvZ.utilities.GameEntity;

import java.awt.event.*;
import java.util.Set;;

public interface GameView {
    void show();
    void update();
    void close();

    boolean isVisible();

    void render(Set<GameEntity> entities, int suns, int kills);
}
