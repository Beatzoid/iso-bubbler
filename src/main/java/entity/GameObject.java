package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import display.Camera;
import game.state.State;

import java.awt.*;

public abstract class GameObject {

    protected Position position;
    protected Position renderOffset;
    protected Position collisionBoxOffset;
    protected Size size;

    protected int renderOrder;

    protected GameObject parent;

    /**
     * The GameObject class manages all GameObjects
     */
    public GameObject() {
        position = new Position(0, 0);
        size = new Size(64, 64);
        renderOffset = new Position(0, 0);
        collisionBoxOffset = new Position(0, 0);
        renderOrder = 5;
    }

    public abstract void update(State state);
    public abstract Image getSprite();
    public abstract CollisionBox getCollisionBox();
    public boolean collidesWith(GameObject other) {
        return getCollisionBox().collidesWith(other.getCollisionBox());
    };

    /**
     * Return the position
     */
    public Position getPosition() {
        Position finalPosition = Position.copyOf(position);

        if (parent != null) {
            finalPosition.add(parent.getPosition());
        }

        return finalPosition;
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

    /**
     * Parent a GameObject
     * @param parent The GameObject to parent
     */
    public void parent(GameObject parent) {
        this.position = new Position(0, 0);
        this.parent = parent;
    }

    public Position getRenderPosition(Camera camera) {
        return new Position(
                getPosition().getX() - camera.getPosition().getX() - renderOffset.getX(),
                getPosition().getY() - camera.getPosition().getY() - renderOffset.getY()
        );
    }

    /**
     * Get the render order
     */
    public int getRenderOrder() {
        return renderOrder;
    }

    /**
     * Clear the parent of the GameObject
     */
    protected void clearParent() {
        parent = null;
    }

    /**
     * Get the render offset
     */
    protected Position getRenderOffset() {
        return renderOffset;
    }

    /**
     * Set the render order
     * @param renderOrder The new render order
     */
    public void setRenderOrder(int renderOrder) {
        this.renderOrder = renderOrder;
    }
}
