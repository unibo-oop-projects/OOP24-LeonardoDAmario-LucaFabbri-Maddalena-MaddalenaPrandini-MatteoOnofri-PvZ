package PvZ.model.api.plants;

/**
 * Enum representing different types of plants in the game.
 * Each plant type has a specific price associated with it.
 */
public enum PlantType {
    /**
     * Represents a Peashooter plant with his life and price.
     */
    PEASHOOTER(50, 100),
    /**
     * Represents a Sunflower plant with his life and price.
     */
    SUNFLOWER(25, 75),
    /**
     * Represents a Wallnut plant with his life and price.
     */
    WALLNUT(75, 200);

    private int price;
    private int life;

    /**
     * Constructor for PlantType enum.
     * 
     * @param price The price of the plant type.
     * @throws IllegalArgumentException if the price is negative.
     * 
     * @param life The life of the plant type.
     * @throws IllegalArgumentException if the life is negative.
     */
    PlantType(final int price, final int life) {
        if (life < 0) {
            throw new IllegalArgumentException("Life cannot be negative.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
        this.life=life;
    }

    /**
     * Gets the price of the plant type.
     * 
     * @return The price of the plant type.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Gets the life of the plant type.
     * 
     * @return The life of the plant type.
     */
    public int getLife() {
        return this.life;
    }
}