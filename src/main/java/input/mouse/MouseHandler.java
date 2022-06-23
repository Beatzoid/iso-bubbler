package input.mouse;

import input.Input;
import input.mouse.action.MouseAction;
import state.State;
import ui.UIImage;

import java.util.Optional;

public class MouseHandler {
    private MouseAction primaryButtonAction;
    private MouseConsumer activeConsumer;

    public void update(State state) {
        final Input input = state.getInput();

        handleActiveConsumer(state, input);
        handlePrimaryButton(state);

        cleanup(input);
    }

    private void handlePrimaryButton(State state) {
        if (primaryButtonAction != null) {
            setActiveConsumer(primaryButtonAction);
            primaryButtonAction.update(state);
        }
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

    public void setPrimaryButtonAction(MouseAction primaryButtonAction) {
        this.primaryButtonAction = primaryButtonAction;
    }

    public Optional<UIImage> getPrimaryButtonUI() {
        if (primaryButtonAction != null) {
            return Optional.ofNullable(primaryButtonAction.getSprite());
        }

        return Optional.empty();
    }
}
