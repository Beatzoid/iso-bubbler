package ui.clickable;

import core.Size;
import state.State;
import ui.UIContainer;
import ui.UIText;
import ui.VerticalContainer;

import java.awt.*;

public class UIButton extends UIClickable {

    private UIContainer container;
    private UIText label;

    private ClickAction clickAction;

    public UIButton(String label, ClickAction clickAction) {
        this.label = new UIText(label);
        this.clickAction = clickAction;

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
    public Image getSprite() {
        return container.getSprite();
    }

    @Override
    protected void onClick(State state) {
        clickAction.execute(state);
    }
}
