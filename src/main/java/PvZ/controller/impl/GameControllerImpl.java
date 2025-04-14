package PvZ.controller.impl;

import PvZ.controller.api.GameController;
import PvZ.model.api.GameModel;
import PvZ.view.api.GameView;

public class GameControllerImpl implements GameController {

    private static final int FPS = 60;
    private static final long TIME_PER_TICK = 1000 / FPS;

    private final GameModel model;
    private final GameView view;
    private boolean running;

    public GameControllerImpl(final GameModel model, final GameView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void startGame() {
        this.running = true;
        new GameLoop().start();
    }

    @Override
    public void stopGame() {
        this.running = false;
    }

    private class GameLoop extends Thread {
        @Override
        public void run() {
            long previousTime = System.currentTimeMillis();

            while (running && view.isVisible()) {
                long currentTime = System.currentTimeMillis();
                long deltaTime = currentTime - previousTime;

                model.update(deltaTime);
                view.render(model);

                previousTime = currentTime;

                waitForNextFrame(currentTime);
            }
        }

        private void waitForNextFrame(final long currentTime) {
            long frameDuration = System.currentTimeMillis() - currentTime;
            if (frameDuration < TIME_PER_TICK) {
                try {
                    Thread.sleep(TIME_PER_TICK - frameDuration);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
