package game.settings;

public class GameSettings {

    private boolean debugMode;
    private double gameSpeedMultiplier;
    private final AudioSettings audioSettings;
    private final RenderSettings renderSettings;


    /**
     * The GameSettings class manages settings for the game.
     *
     * @param debugMode Whether to enable debug mode
     */
    public GameSettings(boolean debugMode) {
        this.debugMode = debugMode;
        audioSettings = new AudioSettings();
        renderSettings = new RenderSettings();
        gameSpeedMultiplier = 1;
    }

    /**
     * Get whether debug mode is enabled
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

    public AudioSettings getAudioSettings() {
        return audioSettings;
    }

    public RenderSettings getRenderSettings() {
        return renderSettings;
    }
}
