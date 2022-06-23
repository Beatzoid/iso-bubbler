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
    private final UIText label;

    private final ClickAction clickAction;

    public UIButton(String label, ClickAction clickAction) {
        this.label = new UIText(label);
        this.clickAction = clickAction;

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
    protected void onFocus(State state) {

    }

    @Override
    public void onDrag(State state) {

    }

    @Override
    public Image getSprite() {
        return container.getSprite();
    }

    @Override
    public void onClick(State state) {
        if (hasFocus) {
            state.getAudioPlayer().playSound("button.wav");
            clickAction.execute(state);
        }
    }
}
