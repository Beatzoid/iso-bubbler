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
        final Input input= state.getInput();

        handlePrimaryButton(state);
        handleActiveConsumer(state, input);

        cleanUp(input);
    }

    private void handlePrimaryButton(State state) {
        if (primaryButtonAction != null) {
            setActiveConsumer(primaryButtonAction);
            primaryButtonAction.update(state);
        }
    }

    private void cleanUp(Input input) {
        if (!input.isMousePressed()) {
            activeConsumer = null;
        }

        input.clearMouseClick();
    }

    private void handleActiveConsumer(State state, Input input) {
        if (activeConsumer != null) {
            if (input.isMouseClicked()) {
                activeConsumer.onClick(state);
            } else if (input.isMousePressed()) {
                activeConsumer.onDrag(state);
            }
        }
    }

    /**
     * Get the active consumer
     */
    public MouseConsumer getActiveConsumer() {
        return activeConsumer;
    }

    /**
     * Set the active consumer
     * @param mouseConsumer The new active consumer
     */
    public void setActiveConsumer(MouseConsumer mouseConsumer) {
        if (activeConsumer == null) {
            activeConsumer = mouseConsumer;
        }
    }

    /**
     * Set the primary button action
     * @param primaryButtonAction The new primary button action
     */
    public void setPrimaryButtonAction(MouseAction primaryButtonAction) {
        this.primaryButtonAction = primaryButtonAction;
    }

    /**
     * Get the primary button UI
     */
    public Optional<UIImage> getPrimaryButtonUI() {
        if (primaryButtonAction != null) {
            return Optional.ofNullable(primaryButtonAction.getSprite());
        }

        return Optional.empty();
    }

    /**
     * Get the primary button action
     */
    public MouseAction getPrimaryButtonAction() {
        return primaryButtonAction;
    }
}
