package state.menu.ui;

import core.Size;
import state.State;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;
import ui.clickable.UISlider;

public class UIOptionsMenu extends VerticalContainer {

    private UISlider musicVolSlider;
    private UIText musicVolLabel;

    /**
     * The UIOptionsMenu manages the option's menu UI
     *
     * @param windowSize The window size
     */
    public UIOptionsMenu(Size windowSize) {
        super(windowSize);
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);

        musicVolSlider = new UISlider(0, 1);
        musicVolLabel = new UIText("");

        addUIComponent(new UIText("OPTIONS"));
        addUIComponent(musicVolLabel);
        addUIComponent(musicVolSlider);
        addUIComponent(new UIButton("Back", (state) -> ((MenuState) state).enterMenu(new UIMainMenu(windowSize))));
    }

    @Override
    public void update(State state) {
        super.update(state);
        handleVolume(state);
    }

    private void handleVolume(State state) {
        state.getGameSettings().getAudioSettings().setMusicVolume((float) musicVolSlider.getValue());
        musicVolLabel.setText(String.format("MUSIC VOL: %d", Math.round(musicVolSlider.getValue() * 100)));
    }
}
