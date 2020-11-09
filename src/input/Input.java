package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private final boolean[] currentlyPressed;
    private final boolean[] pressed;

    /**
     * The Input class handles input
     */
    public Input() {
        pressed = new boolean[255];
        currentlyPressed = new boolean[255];
    }

    public boolean isPressed(int keyCode) {
        if (!pressed[keyCode] && currentlyPressed[keyCode]) {
            pressed[keyCode] = true;
            return true;
        }

        return false;
    }

    /**
     * Checks if a key is pressed
     * @param keyCode The key code to check
     */
    public boolean isCurrentlyPressed(int keyCode) {
        return currentlyPressed[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * This event fires when a key is pressed, and updates the pressed boolean array
     */
    @Override
    public void keyPressed(KeyEvent e) {
        currentlyPressed[e.getKeyCode()] = true;
    }

    /**
     * This event fires when a key is released, and updates the pressed boolean array
     */
    @Override
    public void keyReleased(KeyEvent e) {
        currentlyPressed[e.getKeyCode()] = false;
        pressed[e.getKeyCode()] = false;
    }
}
