package game.state;

import core.Position;
import core.Size;
import display.Camera;
import entity.GameObject;
import game.Time;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;
import ui.UIContainer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class State {

    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected List<UIContainer> uiContainers;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Time time;

    /**
     * The State class manages a bunch of different states
     * @param windowSize The windowSize
     * @param input The input
     *
     * @see Size
     * @see Input
     */
    public State(Size windowSize, Input input) {
        this.input = input;
        gameObjects = new ArrayList<>();
        uiContainers = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        camera = new Camera(windowSize);
        time = new Time();
    }

    public void update() {
        time.update();
        sortObjectsByPosition();
        gameObjects.forEach(gameObject -> gameObject.update(this));
        uiContainers.forEach(uiContainer -> uiContainer.update(this));
        camera.update(this);
    }

    private void sortObjectsByPosition() {
        gameObjects.sort(Comparator.comparing(gameObject -> gameObject.getPosition().getY()));
    }

    /**
     * Get the GameObjects
     */
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * Get the GameMap
     */
    public GameMap getGameMap() {
        return gameMap;
    }

    /**
     * Get the Camera
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Get Time
     */
    public Time getTime() {
        return time;
    }

    /**
     * Get a random position on the GameMap
     */
    public Position getRandomPosition() {
        return gameMap.getRandomPosition();
    }

    /**
     * Get all GameObjects that are colliding with another GameObject
     * @param gameObject The GameObject to check
     */
    public List<GameObject> getCollidingGameObjects(GameObject gameObject) {
        return gameObjects.stream()
                .filter(other -> other.collidesWith(gameObject))
                .collect(Collectors.toList());
    }

    /**
     * Get the UI Containers
     */
    public List<UIContainer> getUiContainers() {
        return uiContainers;
    }
}
