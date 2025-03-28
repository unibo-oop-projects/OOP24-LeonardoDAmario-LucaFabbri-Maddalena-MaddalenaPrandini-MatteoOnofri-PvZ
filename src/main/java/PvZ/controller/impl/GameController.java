package PvZ.controller.impl;

import java.util.List;

import PvZ.model.api.Entity;
import PvZ.model.impl.GameModelImpl;

public class GameController {

    private final GameModelImpl currentGame;

    GameController(final GameModelImpl game) {
        this.currentGame = game;
    }

    void update() {
        List<Entity> entityList = this.currentGame.getEntityList();
        entityList.forEach(entity -> entity.update());
    }
}