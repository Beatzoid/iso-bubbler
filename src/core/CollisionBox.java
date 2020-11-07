package core;

import java.awt.*;

public class CollisionBox {

    private Rectangle bounds;

    /**
     * The CollisionBox class manages collision box's
     * @param bounds The bounds of the collision box
     */
    public CollisionBox(Rectangle bounds) {
        this.bounds = bounds;
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
