package state.menu.ui;

import core.Size;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

public class UIOptionsMenu extends VerticalContainer {

    /**
     * The UIOptionsMenu manages the option's menu UI
     *
     * @param windowSize The window size
     */
    public UIOptionsMenu(Size windowSize) {
        super(windowSize);
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);

        addUIComponent(new UIText("OPTIONS"));
        addUIComponent(new UIButton("Back", (state) -> ((MenuState) state).enterMenu(new UIMainMenu(windowSize))));
    }
}
