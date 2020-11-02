package display;

import game.Game;

import java.awt.*;

public class Renderer {

    public void render(Game game, Graphics graphics) {
        game.getGameObjects().forEach(gameObject -> graphics.drawImage(
                gameObject.getSprite(),
                gameObject.getPosition().intX(),
                gameObject.getPosition().intY(),
                null
        ));
    }
}
