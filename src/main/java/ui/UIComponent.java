package ui;

import core.Position;
import core.Size;
import game.state.State;

import java.awt.*;

public abstract class UIComponent {

    protected Position relativePosition;
    protected Position absolutePosition;
    protected Size size;
    protected Spacing margin;
    protected Spacing padding;

    public UIComponent() {
        relativePosition = new Position(0, 0);
        absolutePosition = new Position(0, 0);

        size = new Size(1, 1);
        margin = new Spacing(0);
        padding = new Spacing(0);
    }

    public abstract Image getSprite();

    public abstract void update(State state);

    /**
     * Get the position of the UI
     */
    public Position getRelativePosition() {
        return relativePosition;
    }

    /**
     * Set the position of the UI
     *
     * @param relativePosition The new position
     * @see Position
     */
    public void setRelativePosition(Position relativePosition) {
        this.relativePosition = relativePosition;
    }

    /**
     * Get the size of the UI
     */
    public Size getSize() {
        return size;
    }

    /**
     * Set the size of the UI
     *
     * @param size The new size
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
     *
     * @param padding The new margin
     * @see Spacing
     */
    public void setPadding(Spacing padding) {
        this.padding = padding;
    }

    /**
     * Get the absolute position of the UI component
     */
    public Position getAbsolutePosition() {
        return absolutePosition;
    }

    /**
     * Set the absolute position of the UI component
     */
    public void setAbsolutePosition(Position absolutePosition) {
        this.absolutePosition = absolutePosition;
    }
}
