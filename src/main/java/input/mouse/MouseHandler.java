package input.mouse;

import input.Input;
import state.State;

public class MouseHandler {
    private MouseConsumer activeConsumer;

    public void update(State state) {
        final Input input = state.getInput();

        handleActiveConsumer(state, input);

        cleanup(input);
    }

    private void cleanup(Input input) {
        if (!input.isMouseCurrentlyPressed()) activeConsumer = null;
        input.clearMouseClick();
    }

    private void handleActiveConsumer(State state, Input input) {
        if (activeConsumer != null) {
            if (input.isMouseClicked()) {
                activeConsumer.onClick(state);
            } else if (input.isMouseCurrentlyPressed()) {
                activeConsumer.onDrag(state);
            }
        }
    }

    public MouseConsumer getActiveConsumer() {
        return activeConsumer;
    }

    public void setActiveConsumer(MouseConsumer mouseConsumer) {
        if (activeConsumer == null) {
            activeConsumer = mouseConsumer;
        }
    }
}
