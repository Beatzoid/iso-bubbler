import game.Game;
import game.GameLoop;

/**
 * The Launcher class is the main class that starts all the code to make the game run
 */
public class Launcher {

    public static void main(String[] args) {
        new Thread(new GameLoop(new Game(800, 600))).start();
    }
}
