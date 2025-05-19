package PvZ.model.api;

public enum Difficulty {
    EASY,
    NORMAL,
    HARD;

    @Override
    public String toString() {
        return switch (this) {
            case EASY -> "Facile";
            case NORMAL -> "Normale";
            case HARD -> "Difficile";
            default -> "";
        };
    }
}
