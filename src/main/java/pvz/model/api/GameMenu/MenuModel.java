package pvz.model.api.GameMenu;

import pvz.model.api.Difficulty;
import java.util.List;

public interface MenuModel {

        Difficulty getSelectedDifficulty();

        void cycleDifficulty();
}
