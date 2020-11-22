package game;

import controller.GameController;
import core.Size;
import display.Display;
import game.settings.GameSettings;
import state.State;
import input.Input;
import state.menu.MenuState;

public class Game {

    public static int SPRITE_SIZE = 64;

    private final Display display;
    private final Input input;
    private State state;
    private final GameSettings settings;
    private final GameController gameController;

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
        settings = new GameSettings(false);
        state = new MenuState(new Size(width, height), input, settings);
        gameController = new GameController(input);
    }

    /**
     * Update the Game
     */
    public void update() {
        state.update(this);
        gameController.update(this);
    }

    /**
     * Render the state on the display
     *
     * @see display.Display
     */
    public void render() {
        display.render(state, settings.isDebugMode());
    }

    /**
     * Get the game settings
     */
    public GameSettings getSettings() {
        return settings;
    }

    /**
     * Enter a new state
     * @param nextState The new state
     */
    public void enterState(State nextState) {
        state.cleanUp();
        state = nextState;
    }
}
