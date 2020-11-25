package game.settings;

public class EditorSettings {

    private final Setting<Boolean> autoTile;

    /**
     * The EditorSettings class manages the settings for the editor
     */
    public EditorSettings() {
        autoTile = new Setting<>(false);
    }

    /**
     * Get whether or not auto tile is enabled
     */
    public Setting<Boolean> getAutoTile() {
        return autoTile;
    }
}
