package core;

public class Position {

    /**
     * The range of how close a NPC has to be to the position before stopping
     */
    public static int PROXIMITY_RANGE = 5;

    private double x;
    private double y;

    /**
     * The position class handles positions. This constructor takes in doubles
     * @param x X
     * @param y Y
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The position class handles positions. This constructor takes in ints
     * @param x X
     * @param y Y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get X as an int
     */
    public int intX() {
        return (int) Math.round(x);
    }

    /**
     * Get Y as an int
     */
    public int intY() {
        return (int) Math.round(y);
    }

    /**
     * Get X as a double
     */
    public double getX() {
        return x;
    }

    /**
     * Get Y as a double
     */
    public double getY() {
        return y;
    }

    /**
     * Apply a motion to the vector
     * @param motion The motion to apply
     *
     * @see Motion
     */
    public void apply(Motion motion) {
        Vector2D vector = motion.getVector();
        x += vector.getX();
        y += vector.getY();
    }

    /**
     * Set X to a new value
     * @param x The new value of X
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set Y to a new value
     * @param y The new value of Y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Get whether a position is within range
     * @param position The position to check
     */
    public boolean isInRangeOf(Position position) {
        return Math.abs(x - position.getX()) < Position.PROXIMITY_RANGE && Math.abs(y - position.getY()) < Position.PROXIMITY_RANGE;
    }
}
