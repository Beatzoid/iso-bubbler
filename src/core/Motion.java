package core;

import controller.EntityController;

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
     * @param entityController The controller to update
     */
    public void update(EntityController entityController) {
        int deltaX = 0;
        int deltaY = 0;

        if(entityController.isRequestingUp()) {
            deltaY--;
        }

        if(entityController.isRequestingDown()) {
            deltaY++;
        }

        if(entityController.isRequestingLeft()) {
            deltaX--;
        }

        if(entityController.isRequestingRight()) {
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
    @SuppressWarnings("unused")
    public void multiply(double multiplier) {
        vector.multiply(multiplier);
    }

    /**
     * Stop the Motion
     *
     * @param stopX Whether to stop on the X axis
     * @param stopY Whether to stop on the Y axis
     */
    public void stop(boolean stopX, boolean stopY) {
        vector = new Vector2D(
                stopX ? 0 : vector.getX(),
                stopY ? 0 : vector.getY()
        );
    }

    /**
     * Get the direction
     */
    public Vector2D getDirection() {
        Vector2D direction = Vector2D.copyOf(vector);
        direction.normalize();

        return direction;
    }

    /**
     * Add a Vector2D to the motion
     * @param vector The Vector2D to add
     */
    public void add(Vector2D vector) {
        this.vector.add(vector);
    }
}
