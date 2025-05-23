package pvz.model.impl.zombies;
import pvz.utilities.Position;

public class ZombieFactory {

    public static ZombieImpl createZombie(String type, Position position) {
        return switch (type.toLowerCase()) {
            case "basic" -> new ZombieImpl(position, 100, 1);
            case "fast" -> new ZombieImpl(position, 80, 2); 
            case "strong" -> new ZombieImpl(position, 150, 1);
            case "beast" -> new ZombieImpl(position, 150, 2);
            default -> throw new IllegalArgumentException("Unsupported zombie type: " + type);
        };
    }
}
