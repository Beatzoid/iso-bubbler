package ui;

public class Alignment {

    public enum Position {
        START, CENTER, END
    }

    private final Position horizontal;
    private final Position vertical;

    /**
     * Align UI Components on the Screen
     * @param vertical The vertical position
     * @param horizontal The horizontal position
     */
    public Alignment(Position horizontal, Position vertical) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    /**
     * Get the horizontal position
     */
    public Position getHorizontal() {
        return horizontal;
    }

    /**
     * Get the vertical position
     */
    public Position getVertical() {
        return vertical;
    }
}
