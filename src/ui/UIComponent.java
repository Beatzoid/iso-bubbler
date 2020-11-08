package ui;

import core.Position;
import core.Size;
import game.state.State;

import java.awt.*;

public abstract class UIComponent {

    protected Position position;
    protected Size size;
    protected Spacing margin;
    protected Spacing padding;

    public UIComponent() {
        position = new Position(0, 0);
        size = new Size(1, 1);
        margin = new Spacing(0);
        padding = new Spacing(5);
    }

    public abstract Image getSprite();
    public abstract void update(State state);

    /**
     * Get the position of the UI
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Set the position of the UI
     * @param position The new position
     *
     * @see Position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Get the size of the UI
     */
    public Size getSize() {
        return size;
    }

    /**
     * Set the size of the UI
     * @param size The new size
     *
     * @see Size
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Get the margin of the UI
     */
    public Spacing getMargin() {
        return margin;
    }

    /**
     * Set the margin of the UI
     * @param margin The new margin
     *
     * @see Spacing
     */
    public void setMargin(Spacing margin) {
        this.margin = margin;
    }

    /**
     * Get the padding of the UI
     */
    public Spacing getPadding() {
        return padding;
    }

    /**
     * Set the padding of the UI
     * @param padding The new margin
     *
     * @see Spacing
     */
    public void setPadding(Spacing padding) {
        this.padding = padding;
    }
}
