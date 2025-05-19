package pvz.controller.impl.Menu;

import pvz.model.api.GameMenu.MenuModel;
import pvz.view.impl.Menu.MenuView;
import pvz.view.impl.Menu.MainGameFrame;

public class MenuController {

    private final MenuModel model;
    private final MenuView view;
    private final MainGameFrame mainFrame;

    public MenuController(MenuModel model, MenuView view, MainGameFrame mainFrame) {
        this.model = model;
        this.view = view;
        this.mainFrame = mainFrame;
        initController();
    }

    private void initController() {
        view.getPlayButton().addActionListener(e -> mainFrame.startGame(model.getSelectedDifficulty()));
        view.getDifficultyButton().addActionListener(e -> {
            model.cycleDifficulty();
            view.updateDifficultyLabel(model.getSelectedDifficulty());
        });
        view.getExitButton().addActionListener(e -> System.exit(0));
    }
}
