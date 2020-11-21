package game.settings;

public class GameSettings {

    private boolean debugMode;
    private double gameSpeedMultiplier;
    private AudioSettings audioSettings;

    /**
     * The GameSettings class manages settings for the game.
     * @param debugMode Whether or not to enable debug mode
     */
    public GameSettings(boolean debugMode) {
        this.debugMode = debugMode;
        gameSpeedMultiplier = 1;
       audioSettings = new AudioSettings();
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
}
