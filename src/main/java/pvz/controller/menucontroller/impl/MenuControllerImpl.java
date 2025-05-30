package pvz.controller.menucontroller.impl;

import pvz.controller.maincontroller.api.MainController;
import pvz.controller.menucontroller.api.MenuController;
import pvz.model.game.api.Difficulty;
import pvz.utilities.Resolution;
import pvz.view.menuview.impl.MenuViewImpl;
import pvz.view.menuview.impl.TutorialView;

public class MenuControllerImpl implements MenuController {

    private MenuViewImpl view;
    private final MainController parentController;
    public MenuControllerImpl(MainController mainController) {

        this.parentController = mainController;
    }

    @Override
    public void openMenu() {
        this.view = new MenuViewImpl(this);
    }

    @Override
    public void closeMenu() {
        if (this.view != null) {
            this.view.dispose();
        }
    }

    @Override
    public void quit() {

    }

    @Override
    public void handleException(Exception exception) {

    }

    public void startGame(Difficulty difficulty, Resolution resolution) {
        parentController.startGame(difficulty, resolution);
        this.closeMenu();
    }

    @Override
    public void showTutorialView(Resolution resolution) {
        new TutorialView(resolution);
    }
}
