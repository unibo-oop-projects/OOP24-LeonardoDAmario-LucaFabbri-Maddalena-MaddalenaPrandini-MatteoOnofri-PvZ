package pvz.model.api.GameMenu;

import pvz.model.api.Difficulty;
import java.util.List;

public interface MenuModel {

        List<MenuOption> getOptions();

        void setSelectedOption(int index);

        int getSelectedIndex();

        MenuOption getSelectedOption();

        Difficulty getSelectedDifficulty();

        void cycleDifficulty();
}
