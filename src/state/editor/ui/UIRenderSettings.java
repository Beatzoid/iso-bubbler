package state.editor.ui;

import core.Size;
import game.settings.RenderSettings;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UICheckbox;

public class UIRenderSettings extends VerticalContainer {

    /**
     * The UIRenderSettings manages the render settings for the map editor
     *
     * @param windowSize The window size
     * @param renderSettings The render settings
     */
    public UIRenderSettings(Size windowSize, RenderSettings renderSettings) {
        super(windowSize);
        setAlignment(new Alignment(Alignment.Position.END, Alignment.Position.START));

        addUIComponent(new UIText("Render settings"));
        addUIComponent(new UICheckbox("GRID", renderSettings.getShouldRenderGrid()));
    }
}
