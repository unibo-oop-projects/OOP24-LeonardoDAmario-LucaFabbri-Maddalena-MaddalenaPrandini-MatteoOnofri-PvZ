package PvZ.model.impl.GameMenu;

import PvZ.model.api.GameMenu.MenuModel;
import PvZ.model.api.GameMenu.MenuOption;

import java.util.Arrays;
import java.util.List;

public class MenuModelImpl implements MenuModel {

    private final List<MenuOption> options = Arrays.asList(MenuOption.values());
    private int selectedIndex = 0;


    @Override
    public List<MenuOption> getOptions() {
        return options;
    }

    @Override
    public void setSelectedOption(int index) {
        if (index >= 0 && index < options.size()) {
            this.selectedIndex = index;
        }

    }

    @Override
    public int getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public MenuOption getSelectedOption() {
        return options.get(selectedIndex);
    }
}