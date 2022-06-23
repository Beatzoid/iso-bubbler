package state.menu;

import core.Size;
import game.settings.GameSettings;
import input.Input;
import map.GameMap;
import state.State;
import state.menu.ui.UIMainMenu;
import ui.UIContainer;

public class MenuState extends State {

    /**
     * The Menu State manages the state of the menu
     *
     * @param windowSize The windowSize
     * @param input      The input
     * @see Size
     * @see Input
     */
    public MenuState(Size windowSize, Input input, GameSettings gameSettings) {
        super(windowSize, input, gameSettings);

        gameMap = new GameMap(new Size(20, 20), spriteLibrary);
        gameSettings.getRenderSettings().getShouldRenderGrid().setValue(false);
        uiContainers.add(new UIMainMenu(windowSize));

        audioPlayer.playMusic("isobubbler.wav");
    }

    public void enterMenu(UIContainer container) {
        uiContainers.clear();

        uiContainers.add(container);
    }
}
