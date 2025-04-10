package PvZ.model.api;

/**
 * Interface for the base plant.
 */
public interface Plant extends Entity {
    /** 
     * Get the plant's health points.
     * @return {@code int} the plant's health points.
     */
    int getLife();

    /**
     * Decrease the plant's health points.
     * @param damage {@code int} the damage to be inflicted.
     */
    void decreaseLife(int damage);

    /**
     * Execute the plant's action.
     */
    void plantAction();

    /**
     * Check if the plant is alive.
     * @return {@code boolean} true if the plant is alive, false otherwise.
     */
    boolean isAlive();

    /**
     * Get the plant's type.
     * 
     * @return {@code PlantType} the plant's type.
     */
    PlantType getType();
}
