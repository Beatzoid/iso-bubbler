package game.settings;

public class GameSettings {

    private boolean debugMode;

    /**
     * The GameSettings class manages settings for the game.
     * @param debugMode Whether or not to enable debug mode
     */
    public GameSettings(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /**
     * Get whether or not debug mode is enabled
     */
    public boolean isDebugMode() {
        return debugMode;
    }
}
