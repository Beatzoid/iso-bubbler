package entity.humanoid.effect;

import entity.humanoid.Humanoid;
import entity.humanoid.action.Cough;
import game.GameLoop;
import game.state.State;

public class Sick extends Effect {

    private static final double COUGH_RATE = 1d / GameLoop.UPDATES_PER_SECOND / 10;

    /**
     * The Sick effect
     */
    public Sick() {
        // This means the Sick effect will last 68 years (not in-game, in real life). If you play for 68 years straight, gg
        super(Integer.MAX_VALUE);
    }

    @Override
    public void update(State state, Humanoid humanoid) {
        super.update(state, humanoid);

        if (Math.random() < COUGH_RATE) {
            humanoid.perform(new Cough());
        }
    }
}
