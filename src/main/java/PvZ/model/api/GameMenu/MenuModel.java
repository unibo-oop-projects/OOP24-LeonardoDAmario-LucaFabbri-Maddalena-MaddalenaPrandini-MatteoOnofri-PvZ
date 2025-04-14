package PvZ.model.api.GameMenu;

import java.util.List;

public interface MenuModel {

        List<MenuOption> getOptions();

        void setSelectedOption(int index);

        int getSelectedIndex();

        MenuOption getSelectedOption();

}
