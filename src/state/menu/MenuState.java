package state.menu;

import core.Size;
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
     * @see Size
     * @see Input
     */
    public MenuState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20, 20), spriteLibrary);

        uiContainers.add(new UIMainMenu(windowSize));
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
