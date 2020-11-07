package game;

import core.Size;
import display.Display;
import game.state.GameState;
import game.state.State;
import input.Input;

public class Game {

    public static int SPRITE_SIZE = 64;

    private final Display display;
    private final Input input;
    private State state;

    /**
     * The Game class handles making a new input, display, and state.
     * It then updates the state and renders the display with the state.
     *
     * @param width The width of the display
     * @param height The height of the display
     */
    public Game(int width, int height) {
        input = new Input();
        display = new Display(width, height, input);
        state = new GameState(new Size(width, height), input);

    }

    /**
     * Update the state
     *
     * @see game.state.State
     */
    public void update() {
        state.update();
    }

    /**
     * Render the state on the display
     *
     * @see display.Display
     */
    public void render() {
        display.render(state);
    }

}
