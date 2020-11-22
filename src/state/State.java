package state;

import audio.AudioPlayer;
import core.Position;
import core.Size;
import display.Camera;
import entity.GameObject;
import game.Game;
import game.Time;
import game.settings.GameSettings;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;
import ui.UIContainer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class State {

    protected GameSettings gameSettings;
    protected AudioPlayer audioPlayer;
    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected List<UIContainer> uiContainers;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Time time;

    protected Size windowSize;

    private State nextState;

    /**
     * The State class manages State of different classes
     * @param windowSize The windowSize
     * @param input The input
     * @param gameSettings The GameSettings
     *
     * @see Size
     * @see Input
     * @see GameSettings
     */
    public State(Size windowSize, Input input, GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        this.windowSize = windowSize;
        this.input = input;
        audioPlayer = new AudioPlayer(gameSettings.getAudioSettings());
        gameObjects = new ArrayList<>();
        uiContainers = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        camera = new Camera(windowSize);
        time = new Time();
    }

    public void update(Game game) {
        audioPlayer.update();
        time.update();
        sortObjectsByPosition();
        updateGameObjects();
        List.copyOf(uiContainers).forEach(uiContainer -> uiContainer.update(this));
        camera.update(this);
        handleMouseInput();

        if (nextState != null) {
            game.enterState(nextState);
        }
    }

    private void handleMouseInput() {
        input.clearMouseClick();
    }

    private void updateGameObjects() {
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).update(this);
        }
    }

    private void sortObjectsByPosition() {
        gameObjects.sort(Comparator.comparing(GameObject::getRenderOrder).thenComparing(gameObject -> gameObject.getPosition().getY()));
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

    /**
     * Get the GameObjects of a certain class
     * @param clazz The class to get
     */
    public <T extends GameObject> List<T> getGameObjectsOfClass(Class<T> clazz) {
        //noinspection unchecked
        return gameObjects.stream()
                .filter(clazz::isInstance)
                .map(gameObject -> (T) gameObject)
                .collect(Collectors.toList());
    }

    /**
     * Get the SpriteLibrary
     */
    public SpriteLibrary getSpriteLibrary() {
        return spriteLibrary;
    }

    /**
     * Spawn a GameObject
     * @param gameObject The GameObject to spawn
     */
    public void spawn(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    /**
     * Get the input
     */
    public Input getInput() {
        return input;
    }

    /**
     * Set the next state
     * @param nextState The next state
     */
    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    /**
     * Get the game settings
     */
    public GameSettings getGameSettings() {
        return gameSettings;
    }

    /**
     * Get the audio player
     */
    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    /**
     * Clean up
     */
    public void cleanUp() {
        audioPlayer.clear();
    }
}
