package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private final boolean[] pressed;

    /**
     * The Input class handles input
     */
    public Input() {
        pressed = new boolean[255];
    }

    /**
     * Checks if a key is pressed
     * @param keyCode The key code to check
     */
    public boolean isPressed(int keyCode) {
        return pressed[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * This event fires when a key is pressed, and updates the pressed boolean array
     */
    @Override
    public void keyPressed(KeyEvent e) {
        pressed[e.getKeyCode()] = true;
    }

    /**
     * This event fires when a key is released, and updates the pressed boolean array
     */
    @Override
    public void keyReleased(KeyEvent e) {
        pressed[e.getKeyCode()] = false;
    }
}
