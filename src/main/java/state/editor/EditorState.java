package state.editor;

import core.Size;
import game.settings.GameSettings;
import input.Input;
import map.GameMap;
import state.State;
import state.editor.ui.UIButtonMenu;
import state.editor.ui.UIRenderSettings;

public class EditorState extends State {

    /**
     * The Editor state manages the state of the editor
     *
     * @param windowSize   The windowSize
     * @param input        The input
     * @param gameSettings The game settings
     * @see Size
     * @see Input
     */
    public EditorState(Size windowSize, Input input, GameSettings gameSettings) {
        super(windowSize, input, gameSettings);

        gameMap = new GameMap(new Size(16, 32), spriteLibrary);
        gameSettings.getRenderSettings().getShouldRenderGrid().setValue(true);

        uiContainers.add(new UIButtonMenu(windowSize));
        uiContainers.add(new UIRenderSettings(windowSize, gameSettings.getRenderSettings(), gameMap));
    }
}
