package state.editor.ui;

import core.Size;
import game.settings.RenderSettings;
import map.GameMap;
import ui.Alignment;
import ui.VerticalContainer;
import ui.clickable.UICheckbox;
import ui.clickable.UIMinimap;

public class UIRenderSettings extends VerticalContainer {

    /**
     * The UIRenderSettings manages the render settings for the map editor
     *
     * @param windowSize The window size
     * @param renderSettings The render settings
     * @param gameMap The GameMap
     */
    public UIRenderSettings(Size windowSize, RenderSettings renderSettings, GameMap gameMap) {
        super(windowSize);
        setAlignment(new Alignment(Alignment.Position.END, Alignment.Position.START));

        addUIComponent(new UIMinimap(gameMap));
        addUIComponent(new UICheckbox("GRID", renderSettings.getShouldRenderGrid()));
    }
}
