package state.menu.ui;

import core.Size;
import game.settings.GameSettings;
import state.State;
import state.menu.MenuState;
import ui.*;
import ui.clickable.UIButton;
import ui.clickable.UISlider;

import java.awt.*;

public class UIOptionsMenu extends VerticalContainer {

    private final UISlider musicVolSlider;
    private final UIText musicVolLabel;

    private final UISlider soundVolSlider;
    private final UIText soundVolLabel;

    /**
     * The UIOptionsMenu manages the option's menu UI
     *
     * @param windowSize The window size
     */
    public UIOptionsMenu(Size windowSize, GameSettings gameSettings) {
        super(windowSize);
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);

        musicVolSlider = new UISlider(0, 1);
        musicVolSlider.setValue(gameSettings.getAudioSettings().getMusicVolume());
        musicVolLabel = new UIText("");

        soundVolSlider = new UISlider(0, 1);
        soundVolSlider.setValue(gameSettings.getAudioSettings().getSoundVolume());
        soundVolLabel = new UIText("");

        UIContainer labelContainer = new VerticalContainer(windowSize);
        labelContainer.setMargin(new Spacing(0));
        labelContainer.setBackgroundColor(Color.DARK_GRAY);
        labelContainer.addUIComponent(new UIText("OPTIONS"));

        UIContainer contentContainer = new VerticalContainer(windowSize);
        contentContainer.setBackgroundColor(Color.DARK_GRAY);
        contentContainer.setMargin(new Spacing(0));
        contentContainer.setPadding(new Spacing(10));

        contentContainer.addUIComponent(musicVolLabel);
        contentContainer.addUIComponent(musicVolSlider);

        contentContainer.addUIComponent(soundVolLabel);
        contentContainer.addUIComponent(soundVolSlider);

        contentContainer.addUIComponent(new UIButton("Back", (state) -> ((MenuState) state).enterMenu(new UIMainMenu(windowSize))));

        addUIComponent(labelContainer);
        addUIComponent(contentContainer);
    }

    @Override
    public void update(State state) {
        super.update(state);
        handleVolume(state);
    }

    private void handleVolume(State state) {
        state.getGameSettings().getAudioSettings().setMusicVolume((float) musicVolSlider.getValue());
        musicVolLabel.setText(String.format("MUSIC VOL: %d", Math.round(musicVolSlider.getValue() * 100)));

        state.getGameSettings().getAudioSettings().setSoundVolume((float) soundVolSlider.getValue());
        soundVolLabel.setText(String.format("SOUND VOL: %d", Math.round(soundVolSlider.getValue() * 100)));
    }
}
