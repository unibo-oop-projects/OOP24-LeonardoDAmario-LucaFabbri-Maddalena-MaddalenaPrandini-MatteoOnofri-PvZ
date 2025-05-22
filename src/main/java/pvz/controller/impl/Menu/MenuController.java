package pvz.controller.impl.Menu;

import pvz.model.api.Difficulty;
import pvz.model.api.GameMenu.MenuModel;
import pvz.view.impl.Game.MainGameFrame;
import pvz.view.impl.Menu.MenuView;

public class MenuController {

    private final MenuModel model;
    private final MenuView view;
    private final MainGameFrame mainFrame;

    private Difficulty currentDifficulty = Difficulty.NORMAL;

    public MenuController(MenuModel model, MenuView view, MainGameFrame mainFrame) {
        this.model = model;
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

        view.getExitButton().addActionListener(e -> System.exit(0));
    }
}
