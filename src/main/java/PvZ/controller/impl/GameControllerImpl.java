package PvZ.controller.impl;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import PvZ.controller.api.GameController;
import PvZ.controller.api.ViewListener;
import PvZ.model.api.GameModel;
import PvZ.model.api.Plants.PlantType;
import PvZ.utilities.Position;
import PvZ.view.api.GameView;

public class GameControllerImpl implements GameController, ViewListener{

    interface Event {}

    record InputRoaster(UserInputRoaster inputRoaster) implements Event {}
    record InputGrid(Position position) implements Event {}

    private static final int FPS = 60;
    private static final long TIME_PER_TICK = 1000 / FPS;

    private final GameModel model;
    private final GameView view;
    private final LinkedBlockingQueue<Event> queue = new LinkedBlockingQueue<>();
    private boolean running;

    public GameControllerImpl(final GameModel model, final GameView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void startGame() {
        this.running = true;
        view.show();
        new GameLoop().start();
    }

    @Override
    public void stopGame() {
        this.running = false;
        view.close();
    }

    private class GameLoop extends Thread {
        PlantType selectedPlantType = null;
        Position selectedPosition = null;

        @Override
        public void run() {
            long previousTime = System.currentTimeMillis();

            while (running && view.isVisible()) {
                long currentTime = System.currentTimeMillis();
                long deltaTime = currentTime - previousTime;

                model.update(deltaTime);
                view.render(model.getGameEntities(), model.getSunCount(), model.getKillCount());

                previousTime = currentTime;

                waitForNextFrame(currentTime);

                handleInput();
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

        private void handleInput(){
            try{
                switch (queue.poll(1, TimeUnit.MILLISECONDS)) {
                    case InputRoaster(var inputRoaster) -> {
                        selectedPlantType = switch (inputRoaster) {
                            case PEASHOOTER -> PlantType.PEASHOOTER;
                            case SUNFLOWER -> PlantType.SUNFLOWER;
                            case WALLNUT -> PlantType.WALLNUT;
                        };
                    }
                    case InputGrid(var positionInput) -> {
                        selectedPosition = new Position(positionInput.x(), positionInput.y());
                    }
                    default -> { }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            if (selectedPlantType != null && selectedPosition != null) {
                model.placePlant(selectedPlantType, selectedPosition);
                selectedPlantType = null;
                selectedPosition = null;
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
}
