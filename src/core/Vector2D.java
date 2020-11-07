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
     * Multiply X and Y by a value
     * @param speed What to multiply X and Y by
     */
    public void multiply(double speed) {
        x *= speed;
        y *= speed;
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
