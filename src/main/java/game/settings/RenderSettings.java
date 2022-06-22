package game.settings;

public class RenderSettings {
    private final Setting<Boolean> shouldRenderGrid;

    public RenderSettings() {
        shouldRenderGrid = new Setting<>(false);
    }

    public Setting<Boolean> getShouldRenderGrid() {
        return shouldRenderGrid;
    }
}
