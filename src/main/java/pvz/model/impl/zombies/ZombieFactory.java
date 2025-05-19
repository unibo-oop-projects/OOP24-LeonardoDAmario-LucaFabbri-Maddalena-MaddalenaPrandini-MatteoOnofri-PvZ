package pvz.model.impl.zombies;

import pvz.model.api.ZombieActionStrategy;
import pvz.utilities.Position;

public class ZombieFactory {

    public static ZombieImpl createZombie(String type, ZombieActionStrategy strategy) {
        return switch (type.toLowerCase()) {
            case "basic" -> BasicZombie.create(strategy);
            case "fast" -> new ZombieImpl(new Position(0, 0), 80, 2, strategy); 
            case "strong" -> new ZombieImpl(new Position(0, 0), 150, 1, strategy);
            default -> throw new IllegalArgumentException("Unsupported zombie type: " + type);
        };
    }
}
