import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Display display;
    private final List<GameObject> gameObjects;

    public Game(int width, int height) {
        display = new Display(width, height);
        gameObjects = new ArrayList<>();
        gameObjects.add(new Square());
    }

    public void update() {
        gameObjects.forEach(GameObject::update);
    }

    public void render() {
        display.render(this);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
