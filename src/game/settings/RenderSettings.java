package game.settings;

public class RenderSettings {

    private final Setting<Boolean> shouldRenderGrid;

    /**
     * The RenderSettings class manages settings for rendering the map editor
     */
    public RenderSettings() {
        shouldRenderGrid = new Setting<>(false);
    }

    /**
     * Get whether or not the grid should be rendered
     */
    public Setting<Boolean> getShouldRenderGrid() {
        return shouldRenderGrid;
    }
}
