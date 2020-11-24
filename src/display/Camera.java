package display;

import core.Position;
import core.Size;
import entity.GameObject;
import game.Game;
import state.State;

import java.awt.*;
import java.util.Optional;

public class Camera {

    private static final int SAFETY_SPACE = 2 * Game.SPRITE_SIZE;

    private Position position;
    private final Size windowSize;

    private Rectangle viewBounds;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<GameObject> objectWithFocus;

    /**
     * The Camera manages following a character
     * @param windowSize The window size
     *
     * @see Size
     */
    public Camera(Size windowSize) {
        this.position = new Position(0, 0);
        this.windowSize = windowSize;
        this.objectWithFocus = Optional.empty();
        calculateViewBounds();
    }

    private void calculateViewBounds() {
        viewBounds = new Rectangle(
                position.intX(),
                position.intY(),
                windowSize.getWidth() + SAFETY_SPACE,
                windowSize.getHeight() + SAFETY_SPACE
        );
    }

    /**
     * Focus on a GameObject
     * @param object The GameObject to focus on
     *
     * @see GameObject
     */
    public void focusOn(GameObject object) {
        this.objectWithFocus = Optional.of(object);
    }

    /**
     * Update the camera
     * @param state The state
     */
    public void update(State state) {
        if(objectWithFocus.isPresent()) {
            Position objectPosition = objectWithFocus.get().getPosition();

            this.position.setX(objectPosition.getX() - windowSize.getWidth() / 2d);
            this.position.setY(objectPosition.getY() - windowSize.getHeight() / 2d);

            clampWithinBounds(state);
            calculateViewBounds();
        }
    }

    private void clampWithinBounds(State state) {
        if (position.getX() < 0) {
            position.setX(0);
        }

        if (position.getY() < 0) {
            position.setY(0);
        }

        if (position.getX() + windowSize.getWidth() > state.getGameMap().getWidth()) {
            position.setX(state.getGameMap().getWidth() - windowSize.getWidth());
        }

        if (position.getY() + windowSize.getHeight() > state.getGameMap().getHeight()) {
            position.setY(state.getGameMap().getHeight() - windowSize.getHeight());
        }
    }

    /**
     * Get the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Get whether a GameObject is within the view of the camera
     * @param gameObject The GameObject to check
     */
    public boolean isInView(GameObject gameObject) {
        return viewBounds.intersects(
                gameObject.getPosition().intX(),
                gameObject.getPosition().intY(),
                gameObject.getSize().getWidth(),
                gameObject.getSize().getHeight()
        );
    }

    /**
     * Get the size
     */
    public Size getSize() {
        return windowSize;
    }

    /**
     * Set the position
     * @param position The new position
     */
    public void setPosition(Position position) {
        this.position = position;
    }
}
