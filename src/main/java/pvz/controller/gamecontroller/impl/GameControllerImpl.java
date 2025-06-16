package pvz.controller.gamecontroller.impl;

import pvz.controller.gamecontroller.api.GameController;
import pvz.controller.gamecontroller.api.ViewListener;
import pvz.controller.maincontroller.api.MainController;
import pvz.utilities.EntityType;
import pvz.utilities.GameEntity;
import pvz.model.game.api.Difficulty;
import pvz.model.game.api.GameModel;
import pvz.model.game.impl.GameModelImpl;
import pvz.utilities.Position;
import pvz.utilities.Resolution;
import pvz.view.gameview.api.GameView;
import pvz.view.gameview.impl.GameViewImpl;

import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class GameControllerImpl implements GameController, ViewListener {

    interface Event {}
    record InputRoaster(UserInputRoaster inputRoaster) implements Event {}
    record InputGrid(Position position) implements Event {}

    private static final int FPS = 60;
    private static final long TIME_PER_TICK = 1000 / FPS;

    private final LinkedBlockingQueue<Event> queue = new LinkedBlockingQueue<>();
    private final MainController parentController;
    private GameModel model;
    private GameView view;
    private boolean running;
    private EntityType selectedPlantType = null;

    public GameControllerImpl(MainController controller) {
        this.parentController = controller;

    }

    @Override
    public void startGame(Difficulty difficulty, Resolution resolution) {
        this.running = true;
        this.model = new GameModelImpl(difficulty);
        this.view = new GameViewImpl(this, resolution);
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

            while (running) {
                long currentTime = System.currentTimeMillis();
                long deltaTime = currentTime - previousTime;

                    model.update(deltaTime);
                    view.render(model.getGameEntities(), model.getSunCount(), model.getKillCount());

                    previousTime = currentTime;

                    waitForNextFrame(currentTime);
                    handleInput();

                    if (model.isGameOver()) {
                        stopGame();
                        parentController.goToEndGame(model.isVictory());
                        view.close();
                    }
            }
        }

        private void waitForNextFrame(final long currentTime) {
            long frameDuration = System.currentTimeMillis() - currentTime;
            if (frameDuration < TIME_PER_TICK) {
                try {
                    Thread.sleep(TIME_PER_TICK - frameDuration);
                } catch (InterruptedException e) {
                    handleException(e);
                }
            }
        }

        private void handleInput() {
            try {
                Event event;
                while ((event = queue.poll(1, TimeUnit.MILLISECONDS)) != null) {
                    switch (event) {
                        case InputRoaster(var inputRoaster) -> {
                            selectedPlantType = switch (inputRoaster) {
                                case PEASHOOTER -> EntityType.PEASHOOTER;
                                case SUNFLOWER -> EntityType.SUNFLOWER;
                                case WALLNUT   -> EntityType.WALLNUT;
                            };
                        }
                        case InputGrid(var pos) -> {
                            if(selectedPlantType != null && !isCellOccupied(pos)){
                                model.placePlant(selectedPlantType, pos);
                            }
                            selectedPlantType = null;
                        }
                        default -> {}
                    }
                }
            } catch (Exception e) {
                handleException(e);
            }
        }
    }

    @Override
    public void processInputRoaster(UserInputRoaster input) {
        queue.add(new InputRoaster(input));
    }

    @Override
    public void processInputGrid(Position position) {
        queue.add(new InputGrid(position)); 
    }

    private boolean isCellOccupied(final Position position) {
        Set<GameEntity> entities = model.getGameEntities();
        return entities.stream().anyMatch(e -> e.position().equals(position)
        && switch (e.type()){
            case PEASHOOTER, SUNFLOWER, WALLNUT -> true;
                    case BASICZOMBIE, STRONGZOMBIE, FASTZOMBIE, BEASTZOMBIE, BULLET, LAWNMOWER -> false;
        }
        );
    }

    @Override
    public void handleException(Exception exception) {
        parentController.handleException(exception);
    }
}