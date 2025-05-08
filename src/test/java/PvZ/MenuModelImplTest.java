package PvZ;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import PvZ.model.api.Difficulty;
import PvZ.model.api.GameMenu.MenuModel;
import PvZ.model.impl.GameMenu.MenuModelImpl;



class MenuModelImplTest {

    private MenuModel model;

    @BeforeEach
    void setUp() {
        model = new MenuModelImpl();
    }

    @Test
    void testInitialDifficultyIsNormal() {
        assertEquals(Difficulty.NORMAL, model.getSelectedDifficulty(), "La difficoltà iniziale dovrebbe essere NORMAL");
    }

    @Test
    void testCycleDifficulty() {
        model.cycleDifficulty();
        assertEquals(Difficulty.HARD, model.getSelectedDifficulty(), "Dopo NORMAL dovrebbe essere HARD");

        model.cycleDifficulty();
        assertEquals(Difficulty.EASY, model.getSelectedDifficulty(), "Dopo HARD dovrebbe essere EASY");

        model.cycleDifficulty();
        assertEquals(Difficulty.NORMAL, model.getSelectedDifficulty(), "Dopo EASY dovrebbe essere NORMAL");
    }
}
