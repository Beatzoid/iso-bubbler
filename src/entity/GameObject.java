package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import game.state.State;

import java.awt.*;

public abstract class GameObject {
    protected Position position;
    protected Size size;

    /**
     * The GameObject class manages all GameObjects
     */
    public GameObject() {
        position = new Position(50, 50);
        size = new Size(50, 50);
    }

    public abstract void update(State state);
    public abstract Image getSprite();
    public abstract CollisionBox getCollisionBox();
    public abstract boolean collidesWith(GameObject other);

    /**
     * Return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Set the position of the GameObject
     * @param position The position to set it to
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Get the size
     */
    public Size getSize() {
        return size;
    }
}
