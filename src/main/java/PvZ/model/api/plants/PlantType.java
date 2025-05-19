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

    private final int price;
    private final int life;

    /**
     * Constructs a PlantType with the specified price and life values.
     *
     * @param price The cost to plant this plant type.
     * @param life  The initial life (health) of this plant type.
     * @throws IllegalArgumentException if price or life are negative.
     */
    PlantType(final int price, final int life) {
        if (life < 0) {
            throw new IllegalArgumentException("Life cannot be negative.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
        this.life = life;
    }

    /**
     * Returns the cost to place this plant in the game.
     *
     * @return The price of the plant.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Returns the maximum life value of this plant.
     *
     * @return The initial life of the plant.
     */
    public int getLife() {
        return this.life;
    }
}
