package pvz.model.zombies.impl;

import java.util.Random;

import pvz.model.game.api.Difficulty;
import pvz.utilities.Position;

public class ZombieSpawnUtil {
    private static final Random random = new Random();
    private static final int SPAWN_POSITION_X = 10;
    

    public static AbstractZombie generateRandomZombie(Difficulty difficulty, int bounds) {
        final Position spawnPosition = new Position(SPAWN_POSITION_X, random.nextInt(bounds));
        String type = getRandomZombieType(difficulty);
        return ZombieFactory.createZombie(type, spawnPosition);
    }

    private static String getRandomZombieType(Difficulty difficulty) {
        int randomValue = random.nextInt(100);

        return switch (difficulty) {
            case EASY -> {
                if (randomValue < 70) yield "basic"; 
                else if (randomValue < 90) yield "fast"; 
                else yield "strong"; 
            }
            case NORMAL -> {
                if (randomValue < 50) yield "basic"; 
                else if (randomValue < 80) yield "fast"; 
                else yield "strong"; 
            }
            case HARD -> {
                if (randomValue < 30) yield "fast"; 
                else if (randomValue < 60) yield "strong"; 
                else yield "beast"; 
            }
        };
    }
        
}
