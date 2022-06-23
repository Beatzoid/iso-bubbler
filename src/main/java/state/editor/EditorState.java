package state.editor;

import core.Size;
import game.settings.GameSettings;
import input.Input;
import input.mouse.action.TilePlacer;
import map.GameMap;
import map.Tile;
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

        gameMap = new GameMap(new Size(64, 64), spriteLibrary);
        gameSettings.getRenderSettings().getShouldRenderGrid().setValue(true);
        mouseHandler.setPrimaryButtonAction(new TilePlacer(new Tile(spriteLibrary, "grass")));

        uiContainers.add(new UIButtonMenu(windowSize));
        uiContainers.add(new UIRenderSettings(windowSize, gameSettings.getRenderSettings(), gameMap));
    }
}
