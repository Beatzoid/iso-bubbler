package ui.clickable;

import core.Position;
import game.state.State;
import ui.UIComponent;

import java.awt.*;

public abstract class UIClickable extends UIComponent {

    protected boolean hasFocus;
    protected boolean isPressed;

    @Override
    public void update(State state) {
        Position mousePosition = state.getInput().getMousePosition();

        hasFocus = getBounds().contains(mousePosition.intX(), mousePosition.intY());
        isPressed = hasFocus && state.getInput().isMouseCurrentlyPressed();

        if (hasFocus && state.getInput().isMouseClicked()) {
            onClick();
        }
    }

    protected abstract void onClick();

    private Rectangle getBounds() {
        return new Rectangle(absolutePosition.intX(), absolutePosition.intY(), size.getWidth(), size.getHeight());
    }
}
