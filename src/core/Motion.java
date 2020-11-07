package core;

import controller.Controller;

public class Motion {

    private Vector2D vector;
    private final double speed;

    /**
     * The Motion class, which handles updating motion for the player and NPC's
     * @param speed The speed
     */
    public Motion(double speed) {
        this.speed = speed;
        this.vector = new Vector2D(0, 0);
    }

    /**
     * Update the motion for a controller
     * @param controller The controller to update the speed
     */
    public void update(Controller controller) {
        int deltaX = 0;
        int deltaY = 0;

        if(controller.isRequestingUp()) {
            deltaY--;
        }

        if(controller.isRequestingDown()) {
            deltaY++;
        }

        if(controller.isRequestingLeft()) {
            deltaX--;
        }

        if(controller.isRequestingRight()) {
            deltaX++;
        }

        vector = new Vector2D(deltaX, deltaY);
        vector.normalize();
        vector.multiply(speed);
    }

    /**
     * Get the vector
     */
    public Vector2D getVector() {
        return vector;
    }

    /**
     * Get whether the vector.length is greater then 0
     */
    public boolean isMoving() {
        return vector.length() > 0;
    }

    /**
     * Multiply the vector
     * @param multiplier How much to multiply the vector by
     */
    public void multiply(double multiplier) {
        vector.multiply(multiplier);
    }

    /**
     * Stop the Motion
     */
    public void stop() {
        vector = new Vector2D(0, 0);
    }
}
