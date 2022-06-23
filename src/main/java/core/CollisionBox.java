package core;

import java.awt.*;

public class CollisionBox {

    private final Rectangle bounds;

    /**
     * The CollisionBox class manages collision box's
     * @param bounds The bounds of the collision box
     */
    public CollisionBox(Rectangle bounds) {
        this.bounds = bounds;
    }

    /**
     * Make a new CollisionBox based on a Position and Size
     * @param position The Position to go off of
     * @param size The Size to go off of
     *
     * @see Position
     * @see Size
     */
    public static CollisionBox of(Position position, Size size) {
        return new CollisionBox(
                new Rectangle(
                        position.intX(),
                        position.intY(),
                        size.getWidth(),
                        size.getHeight()
                )
        );
    }

    /**
     * Get whether or not a CollisionBox is intersecting with another CollisionBox
     * @param other The CollisionBox to check
     */
    public boolean collidesWith(CollisionBox other) {
        return bounds.intersects(other.getBounds());
    }

    /**
     * Get the bounds
     */
    public Rectangle getBounds() {
        return bounds;
    }
}
