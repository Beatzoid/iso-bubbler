package game.state;

import controller.PlayerController;
import core.Size;
import entity.Player;
import input.Input;
import map.GameMap;

public class GameState extends State {

    public GameState(Input input) {
        super(input);
        gameObjects.add(new Player(new PlayerController(input), spriteLibrary));
        gameMap = new GameMap(new Size(20, 20), spriteLibrary);
    }
}
