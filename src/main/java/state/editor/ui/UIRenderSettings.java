package state.editor.ui;

import core.Size;
import game.settings.RenderSettings;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UICheckbox;

public class UIRenderSettings extends VerticalContainer {

    /**
     * The UIRenderSettings manages the render settings for the editor state
     *
     * @param windowSize The window size
     */
    public UIRenderSettings(Size windowSize, RenderSettings renderSettings) {
        super(windowSize);
        setAlignment(new Alignment(Alignment.Position.END, Alignment.Position.START));

        addUIComponent(new UIText("Render Settings"));
        addUIComponent(new UICheckbox("Grid", renderSettings.getShouldRenderGrid()));
    }
}
