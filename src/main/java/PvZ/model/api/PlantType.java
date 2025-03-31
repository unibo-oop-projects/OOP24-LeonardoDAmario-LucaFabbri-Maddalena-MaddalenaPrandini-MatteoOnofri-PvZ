package PvZ.model.api;

/**
 * Enum representing different types of plants in the game.
 * Each plant type has a specific price associated with it.
 */
public enum PlantType {
    /**
     * Represents a Peashooter plant.
     */
    PEASHOOTER(10),
    /**
     * Represents a Sunflower plant.
     */
    SUNFLOWER(20),
    /**
     * Represents a Wallnut plant.
     */
    WALLNUT(30);

    private int price;

    /**
     * Constructor for PlantType enum.
     * 
     * @param price The price of the plant type.
     * @throws IllegalArgumentException if the price is negative.
     */
    PlantType(final int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    /**
     * Gets the price of the plant type.
     * 
     * @return The price of the plant type.
     */
    public int getPrice() {
        return this.price;
    }
}