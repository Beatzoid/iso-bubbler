package state.game.ui;

import core.Size;
import state.game.GameState;
import state.State;
import ui.*;

public class UISicknessStats extends HorizontalContainer {

    private final UIText numberOfSick;
    private final UIText numberOfHealthy;

    /**
     * The UISicknessStats class displays the number of sick people on the UI
     *
     * @param windowSize The window size
     */
    public UISicknessStats(Size windowSize) {
        super(windowSize);
        this.numberOfSick = new UIText("");
        this.numberOfHealthy = new UIText("");

        UIContainer sickContainer = new VerticalContainer(windowSize);
        sickContainer.setPadding(new Spacing(0));
        sickContainer.addUIComponent(new UIText("SICK"));
        sickContainer.addUIComponent(numberOfSick);

        UIContainer healthyContainer = new VerticalContainer(windowSize);
        healthyContainer.setPadding(new Spacing(0));
        healthyContainer.addUIComponent(new UIText("HEALTHY"));
        healthyContainer.addUIComponent(numberOfHealthy);

        addUIComponent(healthyContainer);
        addUIComponent(sickContainer);
    }

    @Override
    public void update(State state) {
        super.update(state);
        if (state instanceof GameState) {
            GameState gameState = (GameState) state;
            numberOfSick.setText(String.format("%d (%d)", gameState.getNumberOfSick(), gameState.getNumberOfIsolated()));
            numberOfHealthy.setText(String.valueOf(gameState.getNumberOfHealthy()));
        }
    }
}
