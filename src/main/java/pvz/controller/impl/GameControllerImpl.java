package pvz.controller.impl;

import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import pvz.controller.api.GameController;
import pvz.controller.api.ViewListener;
import pvz.model.api.GameModel;
import pvz.model.api.plants.PlantType;
import pvz.utilities.GameEntity;
import pvz.utilities.Position;
import pvz.view.api.GameView;
import pvz.view.impl.Game.MainGameFrame;

import javax.swing.*;

public class GameControllerImpl implements GameController, ViewListener {

    interface Event {}
    record InputRoaster(UserInputRoaster inputRoaster) implements Event {}
    record InputGrid(Position position) implements Event {}

    private static final int FPS = 60;
    private static final long TIME_PER_TICK = 1000 / FPS;

    private final GameModel model;
    private final GameView view;
    private final MainGameFrame mainFrame;
    private final LinkedBlockingQueue<Event> queue = new LinkedBlockingQueue<>();
    private boolean running;

    private Position pendingPosition = null;
    private PlantType selectedPlantType = null;

    public GameControllerImpl(final GameModel model, final GameView view, final MainGameFrame mainFrame) {
        this.model = model;
        this.view = view;
        this.view.setViewListener(this);
        this.mainFrame = mainFrame;
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

                    model.update(deltaTime);
                    view.render(model.getGameEntities(), model.getSunCount(), model.getKillCount());

                    previousTime = currentTime;

                    waitForNextFrame(currentTime);
                    System.out.println("[GAMELOOP] Running frame");
                    handleInput();

                    if (model.isGameOver()) {
                        stopGame();
                        System.out.println("Game Over");
                        mainFrame.showEndGameView(model.isVictory());

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
                            if(selectedPlantType != null && !isCellOccupied(pos)){
                                model.placePlant(selectedPlantType, pos);
                            }
                            selectedPlantType = null;
                        }
                        default -> {}
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
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
            case ZOMBIE, BULLET -> false;
        }
        );
    }
}