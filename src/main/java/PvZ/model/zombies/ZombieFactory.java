package PvZ.model.zombies;

import PvZ.model.api.ZombieActionStrategy;

public class ZombieFactory {

    public static ZombieImpl createZombie(String type, ZombieActionStrategy strategy) {
        return switch (type.toLowerCase()) {
            case "basic" -> BasicZombie.create(strategy);
            case "fast" -> new ZombieImpl(80, 2, strategy); 
            case "strong" -> new ZombieImpl(150, 1, strategy);
            default -> throw new IllegalArgumentException("Tipo di zombie non supportato: " + type);
        };
    }
}
