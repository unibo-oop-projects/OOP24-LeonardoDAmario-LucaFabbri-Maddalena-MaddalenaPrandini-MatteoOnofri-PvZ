package pvz.model.api.GameMenu;

public enum MenuOption {
    NUOVA_PARTITA("Nuova Partita"),
    OPZIONI("Opzioni"),
    ESCI("Esci");

    private final String label;

    MenuOption(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
