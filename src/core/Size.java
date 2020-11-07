package core;

public class Size {
    private final int width;
    private final int height;

    /**
     * The size class handles width and height
     * @param width The width
     * @param height The height
     */
    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Get the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height
     */
    public int getHeight() {
        return height;
    }
}
