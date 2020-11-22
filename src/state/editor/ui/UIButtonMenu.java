package state.editor.ui;

import core.Size;
import state.menu.MenuState;
import ui.HorizontalContainer;
import ui.clickable.UIButton;

public class UIButtonMenu extends HorizontalContainer {

    /**
     * The UIButtonMenu class
     *
     * @param windowSize The window size
     */
    public UIButtonMenu(Size windowSize) {
        super(windowSize);

        addUIComponent(new UIButton("MAIN MENU", state -> state.setNextState(new MenuState(state.getCamera().getSize(), state.getInput(), state.getGameSettings()))));
    }
}
