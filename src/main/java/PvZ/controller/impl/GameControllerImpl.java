package PvZ.controller.impl;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import PvZ.controller.api.GameController;
import PvZ.controller.api.ViewListener;
import PvZ.model.api.GameModel;
import PvZ.model.api.Plants.PlantType;
import PvZ.utilities.Position;
import PvZ.view.api.GameView;

import javax.swing.*;

public class GameControllerImpl implements GameController, ViewListener {

    interface Event {}
    record InputView (boolean isVisible) implements  Event{}
    record InputRoaster(UserInputRoaster inputRoaster) implements Event {}
    record InputGrid(Position position) implements Event {}

    private static final int FPS = 60;
    private static final long TIME_PER_TICK = 1000 / FPS;

    private final GameModel model;
    private final GameView view;
    private final LinkedBlockingQueue<Event> queue = new LinkedBlockingQueue<>();
    private boolean running;

    private Position pendingPosition = null;
    private PlantType selectedPlantType = null;
    private boolean viewVisibility = false;

    public GameControllerImpl(final GameModel model, final GameView view) {
        this.model = model;
        this.view = view;
        this.view.setViewListener(this);
    }

    @Override
    public void startGame() {
        this.running = true;
        view.show();

        SwingUtilities.invokeLater(() ->
                SwingUtilities.invokeLater(() ->
                        new GameLoop().start()
                )
        );
    }


    @Override
    public void stopGame() {
        this.running = false;
        view.close();
    }

    private class GameLoop extends Thread {
        @Override
        public void run() {
            System.out.println("[GAMELOOP] Starting main loop");
            long previousTime = System.currentTimeMillis();

            while (running) {
                long currentTime = System.currentTimeMillis();
                long deltaTime = currentTime - previousTime;

                if(viewVisibility) {
                    model.update(deltaTime);
                    view.render(model.getGameEntities(), model.getSunCount(), model.getKillCount());

                    previousTime = currentTime;

                    waitForNextFrame(currentTime);
                    System.out.println("[GAMELOOP] Running frame");
                    handleInput();
                }

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

        private void handleInput() {
            System.out.println("[CONTROLLER] Polling input...");
            try {
                Event event;
                while ((event = queue.poll(1, TimeUnit.MILLISECONDS)) != null) {
                    switch (event) {
                        case InputRoaster(var inputRoaster) -> {
                            selectedPlantType = switch (inputRoaster) {
                                case PEASHOOTER -> PlantType.PEASHOOTER;
                                case SUNFLOWER -> PlantType.SUNFLOWER;
                                case WALLNUT   -> PlantType.WALLNUT;
                            };
                            System.out.println("[CONTROLLER] Selected plant type: " + selectedPlantType);
                        }
                        case InputGrid(var pos) -> {
                            pendingPosition = pos;
                            System.out.println("[CONTROLLER] Selected position: " + pendingPosition);
                        }
                        case InputView(var inputView) -> {
                            viewVisibility = inputView;
                        }
                        default -> {}
                    }
                    createPlant();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createPlant() {
        if (selectedPlantType != null && pendingPosition != null) {
            model.placePlant(selectedPlantType, pendingPosition);
            selectedPlantType = null;
            pendingPosition = null;
        }
    }

    @Override
    public void processInputRoaster(UserInputRoaster input) {
        queue.add(new InputRoaster(input));
    }

    @Override
    public void processInputView(boolean isVisible) {
        queue.add(new InputView(isVisible));
    }

    @Override
    public void processInputGrid(Position position) {
        queue.add(new InputGrid(position)); 
    }
}