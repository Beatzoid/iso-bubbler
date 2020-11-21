package state.menu.ui;

import core.Size;
import state.State;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;
import ui.clickable.UISlider;

public class UIOptionMenu extends VerticalContainer {

    private UISlider musicVolSider;
    private UIText musicVolLabel;

    /**
     * The UIOptionMenu manages the option menu UI
     *
     * @param windowSize The window size
     */
    public UIOptionMenu(Size windowSize) {
        super(windowSize);
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);

        musicVolSider = new UISlider(0, 1);
        musicVolLabel = new UIText("");

        addUIComponent(new UIText("Options"));
        addUIComponent(musicVolLabel);
        addUIComponent(musicVolSider);
        addUIComponent(new UIButton("Back", (state) -> ((MenuState)state).enterMenu(new UIMainMenu(windowSize))));
    }

    @Override
    public void update(State state) {
        super.update(state);
        handleVolume(state);
    }

    private void handleVolume(State state) {
        state.getGameSettings().getAudioSettings().setMusicVolume((float) musicVolSider.getValue());
        musicVolLabel.setText(String.format("Music Vol: %d", Math.round(musicVolSider.getValue() * 100)));
    }
}
