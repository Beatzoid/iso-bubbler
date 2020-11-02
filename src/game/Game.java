package game;

import controller.PlayerController;
import display.Display;
import entity.GameObject;
import entity.Player;
import input.Input;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Display display;
    private final List<GameObject> gameObjects;
    private final Input input;

    public Game(int width, int height) {
        input = new Input();
        display = new Display(width, height, input);
        gameObjects = new ArrayList<>();
        gameObjects.add(new Player(new PlayerController(input)));
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