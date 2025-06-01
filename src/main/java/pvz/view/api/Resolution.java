package pvz.view.api;

import java.awt.Dimension;

public enum Resolution {

    R640x480(640, 480, "640×480 (VGA)"),
    R800x600(800, 600, "800×600 (SVGA)"),
    R1024x768(1024, 768, "1024×768 (XGA)"),
    R1152x864(1152, 864, "1152×864 (SXGA-)"),
    R1600x1200(1600, 1200, "1600×1200 (UXGA)"),
    R2048x1536(2048, 1536, "2048×1536 (QXGA)");


    private final int width;
    private final int height;
    private final String label;

    Resolution(int width, int height, String label) {
        this.width = width;
        this.height = height;
        this.label = label;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Dimension getDimension() {
        return new Dimension(width, height);
    }

    @Override
    public String toString() {
        return label;
    }
}
