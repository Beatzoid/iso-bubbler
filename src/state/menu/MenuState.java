package state.menu;

import core.Size;
import game.Game;
import game.settings.GameSettings;
import input.Input;
import map.GameMap;
import state.State;
import state.menu.ui.UIMainMenu;
import ui.UIContainer;

public class MenuState extends State {

    /**
     * The MenuState class manages the state of the menu
     *
     * @param windowSize The windowSize
     * @param input      The input
     * @param gameSettings The game settings
     *
     * @see Size
     * @see Input
     * @see GameSettings
     */

    public MenuState(Size windowSize, Input input, GameSettings gameSettings) {
        super(windowSize, input, gameSettings);
        gameMap = new GameMap(new Size(20, 20), spriteLibrary);
        gameSettings.getRenderSettings().getShouldRenderGrid().setValue(false);

        uiContainers.add(new UIMainMenu(windowSize));

        audioPlayer.playMusic("isobubbler.wav");
    }

    /**
     * Enter a new menu state
     * @param container The UIContainer
     */
    public void enterMenu(UIContainer container) {
        uiContainers.clear();
        uiContainers.add(container);
    }
}
