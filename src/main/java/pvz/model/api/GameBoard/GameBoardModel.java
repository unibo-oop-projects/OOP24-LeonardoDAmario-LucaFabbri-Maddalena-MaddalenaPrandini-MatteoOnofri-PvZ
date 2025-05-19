package pvz.model.api.GameBoard;

import pvz.model.api.entities.Entity;

public interface GameBoardModel {
    int getRows();
    int getCols();
    Entity getEntityAt(int row, int col);
    void setEntityAt(int row, int col, Entity entity);
    void removeEntityAt(int row, int col);
}
