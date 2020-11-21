package ui.clickable;

import core.Size;
import game.state.State;
import ui.UIContainer;
import ui.UIText;
import ui.VerticalContainer;

import java.awt.*;

public class UIButton extends UIClickable {

    private final UIContainer container;
    private final UIText label;

    private final Runnable clickEvent;

    /**
     * The UIButton class displays and handles buttons on the screen
     * @param label The label for the button
     * @param clickEvent The Runnable to run when the button is clicked
     *
     * @see Runnable
     */
    public UIButton(String label, Runnable clickEvent) {
        this.label = new UIText(label);
        this.clickEvent = clickEvent;

        container = new VerticalContainer(new Size(0, 0));
        container.addUIComponent(this.label);
        container.setFixedSize(new Size(150, 40));
    }

    @Override
    public void update(State state) {
        super.update(state);
        container.update(state);
        size = container.getSize();

        Color color = Color.GRAY;

        if (hasFocus) {
            color = Color.LIGHT_GRAY;
        }

        if (isPressed) {
            color = Color.DARK_GRAY;
        }

        container.setBackgroundColor(color);
    }

    @Override
    protected void onClick() {
        clickEvent.run();
    }

    @Override
    public Image getSprite() {
        return container.getSprite();
    }
}
