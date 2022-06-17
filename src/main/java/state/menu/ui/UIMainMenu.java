package state.menu.ui;

import core.Size;
import state.game.GameState;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

public class UIMainMenu extends VerticalContainer {
    /**
     * The UIMainMenu manages the Main Menu UI
     *
     * @param windowSize The window size
     */
    public UIMainMenu(Size windowSize) {
        super(windowSize);
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);


        addUIComponent(new UIText("Isobubbler"));
        addUIComponent(new UIButton("Play", (state) -> state.setNextState(new GameState(windowSize, state.getInput(), state.getGameSettings()))));
        addUIComponent(new UIButton("Options", (state) -> ((MenuState) state).enterMenu(new UIOptionsMenu(windowSize, state.getGameSettings()))));
        addUIComponent(new UIButton("Exit", (state) -> System.exit(0)));
    }
}
