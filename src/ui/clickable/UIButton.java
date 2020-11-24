package ui.clickable;

import core.Size;
import state.State;
import ui.Spacing;
import ui.UIContainer;
import ui.UIText;
import ui.VerticalContainer;

import java.awt.*;

public class UIButton extends UIClickable {

    private final UIContainer container;
    @SuppressWarnings("FieldCanBeLocal")
    private final UIText label;

    private final ClickAction clickAction;

    /**
     * The UIButton class displays and handles buttons on the screen
     * @param label The label for the button
     * @param clickEvent The Runnable to run when the button is clicked
     *
     * @see Runnable
     */
    public UIButton(String label, ClickAction clickEvent) {
        this.label = new UIText(label);
        this.clickAction = clickEvent;

        setMargin(new Spacing(5, 0, 0, 0));

        container = new VerticalContainer(new Size(0, 0));
        container.setCenterChildren(true);
        container.addUIComponent(this.label);
        container.setFixedSize(new Size(200, 40));
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
    protected void onFocus(State state) {}

    @Override
    public void onDrag(State state) {}

    @Override
    public void onClick(State state) {
        state.getAudioPlayer().playSound("button.wav");

        if (hasFocus) {
            clickAction.execute(state);
        }
    }

    @Override
    public Image getSprite() {
        return container.getSprite();
    }
}
