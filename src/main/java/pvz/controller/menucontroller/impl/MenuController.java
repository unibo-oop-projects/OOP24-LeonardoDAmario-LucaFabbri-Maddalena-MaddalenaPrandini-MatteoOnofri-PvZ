package pvz.controller.menucontroller.impl;

import pvz.model.game.api.Difficulty;
import pvz.view.gameview.impl.MainGameFrame;
import pvz.view.menuview.impl.MenuViewImpl;
import pvz.utilities.Resolution;

public class MenuController implements pvz.controller.menucontroller.api.MenuController {

    private final MenuViewImpl view;
    private final MainGameFrame mainFrame;

    private Difficulty currentDifficulty = Difficulty.NORMAL;

    public MenuController(MenuViewImpl view, MainGameFrame mainFrame) {

        this.view = view;
        this.mainFrame = mainFrame;

        initListeners();
    }

    private void initListeners() {
        view.getPlayButton().addActionListener(e -> {
            mainFrame.startGame(currentDifficulty);
        });

        view.getDifficultyButton().addActionListener(e -> {
            currentDifficulty = switch (currentDifficulty) {
                case NORMAL -> Difficulty.HARD;
                case HARD -> Difficulty.EASY;
                case EASY -> Difficulty.NORMAL;
            };
            view.updateDifficultyLabel(currentDifficulty);
        });

        view.getTutorialButton().addActionListener(e -> mainFrame.showTutorialView());

        view.getExitButton().addActionListener(e -> System.exit(0));

        view.addResolutionListener(e -> {
            Resolution sel = view.getSelectedResolution();
            mainFrame.setResolution(sel);
        });

    }

    @Override
    public void openMenu() {

    }

    @Override
    public void closeMenu() {

    }

    @Override
    public void quit() {

    }

    @Override
    public void handleException(Exception exception) {

    }
}
