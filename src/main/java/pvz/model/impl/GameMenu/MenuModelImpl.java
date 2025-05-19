package pvz.model.impl.GameMenu;

import pvz.model.api.Difficulty;
import pvz.model.api.GameMenu.MenuModel;
import pvz.model.api.GameMenu.MenuOption;

import java.util.List;

public class MenuModelImpl implements MenuModel {

    private Difficulty selectedDifficulty = Difficulty.NORMAL;

    @Override
    public List<MenuOption> getOptions() {
        return List.of();
    }

    @Override
    public void setSelectedOption(int index) {

    }

    @Override
    public int getSelectedIndex() {
        return 0;
    }

    @Override
    public MenuOption getSelectedOption() {
        return null;
    }

    @Override
    public Difficulty getSelectedDifficulty() {
        return selectedDifficulty;
    }

    @Override
    public void cycleDifficulty() {
        switch (selectedDifficulty) {
            case EASY:
                selectedDifficulty = Difficulty.NORMAL;
                break;
            case NORMAL:
                selectedDifficulty = Difficulty.HARD;
                break;
            case HARD:
                selectedDifficulty = Difficulty.EASY;
                break;
        }
    }
}
