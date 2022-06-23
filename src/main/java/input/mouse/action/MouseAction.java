package input.mouse.action;

import input.mouse.MouseConsumer;
import state.State;
import ui.UIImage;

public abstract class MouseAction implements MouseConsumer {
    public abstract void update(State state);

    public abstract UIImage getSprite();
}
