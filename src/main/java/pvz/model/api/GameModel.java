package pvz.model.api;

import pvz.model.api.plants.PlantType;
import pvz.utilities.GameEntity;
import pvz.utilities.Position;

import java.util.Set;

/**
 * Interface representing the core game model for the Plants vs. Zombies game.
 * <p>
 * Provides methods for controlling the game lifecycle, placing plants, updating game state,
 * and retrieving key information such as sun count, kills, and game entities.
 */
public interface GameModel {


    /**
     * Checks if the game is over.
     *
     * @return {@code true} if the game has ended, {@code false} otherwise.
     */
    boolean isGameOver();

    /**
     * Checks if the player has won the game.
     *
     * @return {@code true} if the player achieved victory, {@code false} otherwise.
     */
    boolean isVictory();

    /**
     * Returns the current status of the game (running, over, victory).
     *
     * @return the {@link GameStatus} representing the game state.
     */
    GameStatus getGameStatus();

    /**
     * Attempts to place a plant of the given type at the specified position on the game field.
     *
     * @param type     the type of plant to place (e.g., sunflower, peashooter).
     * @param position the grid position where the plant should be placed.
     */
    void placePlant(PlantType type, Position position);

    /**
     * Updates the entire game state based on the elapsed time since the last update.
     *
     * @param deltaTime the time passed since the last update (in milliseconds).
     */
    void update(long deltaTime);

    /**
     * Returns a set of all current game entities, including plants, zombies, and projectiles.
     *
     * @return a {@link Set} of {@link GameEntity} objects representing all active entities.
     */
    Set<GameEntity> getGameEntities();

    /**
     * Returns the current amount of sun resources available to the player.
     *
     * @return the number of sun points.
     */
    int getSunCount();

    /**
     * Returns the number of zombies the player has defeated.
     *
     * @return the total kill count.
     */
    int getKillCount();
}
