package controller;
import input.Input;

import java.awt.event.KeyEvent;

public class PlayerController implements EntityController {

    /**
     * The input
     *
     * @see Input
     */
    private final Input input;

    /**
     * The controller that controls the player
     * @param input A Input for the player
     *
     * @see Input
     */
    public PlayerController(Input input) {
        this.input = input;
    }

    @Override
    public boolean isRequestingUp() {
        return input.isCurrentlyPressed(KeyEvent.VK_UP);
    }

    @Override
    public boolean isRequestingDown() {
        return input.isCurrentlyPressed(KeyEvent.VK_DOWN);
    }

    @Override
    public boolean isRequestingLeft() {
        return input.isCurrentlyPressed(KeyEvent.VK_LEFT);
    }

    @Override
    public boolean isRequestingRight() {
        return input.isCurrentlyPressed(KeyEvent.VK_RIGHT);
    }

    @Override
    public boolean isRequestingAction() {
        return input.isPressed(KeyEvent.VK_SPACE);
    }
}
