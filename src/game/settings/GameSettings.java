package game.settings;

public class GameSettings {

    private boolean debugMode;
    private double gameSpeedMultiplier;
    private final AudioSettings audioSettings;
    private final RenderSettings renderSettings;
    private final EditorSettings editorSettings;

    /**
     * The GameSettings class manages settings for the game.
     * @param debugMode Whether or not to enable debug mode
     */
    public GameSettings(boolean debugMode) {
        this.debugMode = debugMode;
        gameSpeedMultiplier = 1;
       audioSettings = new AudioSettings();
       renderSettings = new RenderSettings();
       editorSettings = new EditorSettings();
    }

    /**
     * Get whether or not debug mode is enabled
     */
    public boolean isDebugMode() {
        return debugMode;
    }

    /**
     * Toggle debug mode on and off
     */
    public void toggleDebugMode() {
        debugMode = !debugMode;
    }

    /**
     * Increase the game speed
     */
    public void increaseGameSpeed() {
        gameSpeedMultiplier += 0.25;
    }

    /**
     * Decrease the game speed
     */
    public void decreaseGameSpeed() {
        gameSpeedMultiplier -= 0.25;
    }

    /**
     * Get the game speed multiplier
     */
    public double getGameSpeedMultiplier() {
        return gameSpeedMultiplier;
    }

    /**
     * Get the audio settings
     */
    public AudioSettings getAudioSettings() {
        return audioSettings;
    }

    /**
     * Get the render settings
     */
    public RenderSettings getRenderSettings() {
        return renderSettings;
    }

    /**
     * Get the editor settings
     */
    public EditorSettings getEditorSettings() {
        return editorSettings;
    }
}
