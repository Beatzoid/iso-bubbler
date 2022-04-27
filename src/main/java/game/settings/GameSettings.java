package game.settings;

public class GameSettings {

    private boolean debugMode;
    private double gameSpeedMultiplier;
    private float musicVolume;
    private float soundVolume;

    /**
     * The GameSettings class manages settings for the game.
     *
     * @param debugMode Whether to enable debug mode
     */
    public GameSettings(boolean debugMode) {
        this.debugMode = debugMode;
        gameSpeedMultiplier = 1;
        musicVolume = 0;
        soundVolume = 0;
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

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }
}
