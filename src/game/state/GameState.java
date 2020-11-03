package game.state;

import controller.PlayerController;
import core.Size;
import entity.Player;
import input.Input;
import map.GameMap;

public class GameState extends State {

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        final Player player = new Player(new PlayerController(input), spriteLibrary);
        gameObjects.add(player);
        gameMap = new GameMap(new Size(20, 20), spriteLibrary);
        camera.focusOn(player);
    }
}
