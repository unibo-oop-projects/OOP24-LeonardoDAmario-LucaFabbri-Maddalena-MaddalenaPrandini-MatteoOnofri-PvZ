package pvz.model.zombies.impl;
import pvz.utilities.Position;

public class ZombieFactory {

    public static AbstractZombie createZombie(String type, Position position) {
        return switch (type.toLowerCase()) {
            case "basic" -> new BasicZombie(position);
            case "fast" -> new FastZombie(position);
            case "strong" -> new StrongZombie(position);
            case "beast" -> new BeastZombie(position);
            default -> throw new IllegalArgumentException("Unsupported zombie type: " + type);
        };
    }
}
