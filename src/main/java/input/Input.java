package input;

import core.Position;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

    private Position mousePosition;
    private boolean mouseClicked;
    private boolean mouseCurrentlyPressed;

    private final boolean[] currentlyPressed;
    private final boolean[] pressed;

    /**
     * The Input class handles input
     */
    public Input() {
        pressed = new boolean[255];
        currentlyPressed = new boolean[255];
        mousePosition = new Position(0, 0);
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
     *
     * @param keyCode The key code to check
     */
    public boolean isCurrentlyPressed(int keyCode) {
        return currentlyPressed[keyCode];
    }

    /**
     * Set mouse clicked to false
     */
    public void clearMouseClick() {
        mouseClicked = false;
    }

    public Position getMousePosition() {
        return mousePosition;
    }

    public boolean isMouseClicked() {
        return mouseClicked;
    }

    public boolean isMouseCurrentlyPressed() {
        return mouseCurrentlyPressed;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

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

    @Override
    public void mousePressed(MouseEvent e) {
        mouseCurrentlyPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClicked = true;
        mouseCurrentlyPressed = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition = new Position(e.getPoint().getX(), e.getPoint().getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition = new Position(e.getPoint().getX(), e.getPoint().getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
