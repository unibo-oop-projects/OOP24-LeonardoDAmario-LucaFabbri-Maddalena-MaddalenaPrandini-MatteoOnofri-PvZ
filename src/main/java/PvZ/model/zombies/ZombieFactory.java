package PvZ.model.zombies;

import PvZ.model.api.ZombieActionStrategy;

public class ZombieFactory {

    public static Zombie createZombie(String type, ZombieActionStrategy strategy) {
        return switch (type.toLowerCase()) {
            case "basic" -> BasicZombie.create(strategy);
            case "fast" -> new Zombie(80, 2, strategy); 
            case "strong" -> new Zombie(150, 1, strategy);
            default -> throw new IllegalArgumentException("Tipo di zombie non supportato: " + type);
        };
    }
}
