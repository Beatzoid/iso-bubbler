package ui;

public class Spacing {

    private int top;
    private int right;
    private int bottom;
    private int left;

    /**
     * Generate spacing using only one int
     * @param spacing The spacing
     */
    public Spacing(int spacing) {
        this(spacing, spacing, spacing, spacing);
    }

    /**
     * Generate spacing using two ints
     * @param horizontal The horizontal (left and right) spacing
     * @param vertical The vertical (top and bottom) spacing
     */
    public Spacing(int horizontal, int vertical) {
        this(vertical, horizontal, vertical, horizontal);
    }

    /**
     * Generate spacing using four ints
     * @param top The top spacing
     * @param right The right spacing
     * @param bottom The bottom spacing
     * @param left The left spacing
     */
    public Spacing(int top, int right, int bottom, int left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    /**
     * Get top spacing
     */
    public int getTop() {
        return top;
    }

    /**
     * Get right spacing
     */
    public int getRight() {
        return right;
    }

    /**
     * Get bottom spacing
     */
    public int getBottom() {
        return bottom;
    }

    /**
     * Get left spacing
     */
    public int getLeft() {
        return left;
    }

    /**
     * Get the vertical spacing
     */
    public int getVertical() {
        return top + bottom;
    }

    /**
     * Get the horizontal spacing
     */
    public int getHorizontal() {
        return left + right;
    }
}
