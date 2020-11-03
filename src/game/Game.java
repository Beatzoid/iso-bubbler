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

    public Game(int width, int height) {
        input = new Input();
        display = new Display(width, height, input);
        state = new GameState(new Size(width, height), input);

    }

    public void update() {
        state.update();
    }

    public void render() {
        display.render(state);
    }

}
