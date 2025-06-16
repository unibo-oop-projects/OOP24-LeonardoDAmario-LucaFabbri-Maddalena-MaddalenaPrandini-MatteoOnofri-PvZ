package pvz.utilities;

import java.awt.*;

/**
 * Enum representing different screen resolutions available in the game.
 */
public enum Resolution {

    R640x480(640, 480, "640×480 (VGA)"),
    R800x600(800, 600, "800×600 (SVGA)"),
    R1024x768(1024, 768, "1024×768 (XGA)"),
    R1152x864(1152, 864, "1152×864 (SXGA-)"),
    R1600x1200(1600, 1200, "1600×1200 (UXGA)"),
    R2048x1536(2048, 1536, "2048×1536 (QXGA)"),
    R3840x2160(3840, 2160, "3840×2160 (4K UHD)");

    private final int width;
    private final int height;
    private final String label;

    /**
     * Constructs a Resolution enum with given dimensions and label.
     *
     * @param width  the width in pixels
     * @param height the height in pixels
     * @param label  the human-readable label for the resolution
     */
    Resolution(int width, int height, String label) {
        this.width = width;
        this.height = height;
        this.label = label;
    }

    /**
     * @return the width of the resolution
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height of the resolution
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the string representation of the resolution label
     */
    @Override
    public String toString() {
        return label;
    }
}
