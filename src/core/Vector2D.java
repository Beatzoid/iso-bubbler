package core;

public class Vector2D {

    private double x;
    private double y;

    /**
     * The Vector2D manages vectors in 2D
     * @param x X
     * @param y Y
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the length of the Vector
     */
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Normalize the vector
     */
    public void normalize() {
        double length = length();

        x = x == 0 ? 0 : x/length;
        y = y == 0 ? 0 : y/length;
    }

    /**
     * Multiply X and Y by a value
     * @param speed What to multiply X and Y by
     */
    public void multiply(double speed) {
        x *= speed;
        y *= speed;
    }

    /**
     * Make a copy of a vector2D
     * @param vector The Vector2D to copy
     */
    public static Vector2D copyOf(Vector2D vector) {
        return new Vector2D(vector.getX(), vector.getY());
    }

    /**
     * Get the direction between two positions
     * @param start Start position
     * @param end End position
     * @return
     */
    public static Vector2D directionBetweenPositions(Position start, Position end) {
        Vector2D direction = new Vector2D(start.getX() - end.getX(), start.getY() - end.getY());
        direction.normalize();

        return direction;
    }

    /**
     * Get the dot product
     * @param v1 First vector
     * @param v2 Second vector
     */
    public static double dotProduct(Vector2D v1, Vector2D v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY();
    }

    /**
     * Get X
     */
    public double getX() {
        return x;
    }

    /**
     * Get Y
     */
    public double getY() {
        return y;
    }
}
