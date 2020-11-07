package entity.action;

import entity.MovingEntity;
import game.GameLoop;
import game.state.State;

public class Cough extends Action {

    private int lifeSpanInSeconds;

    /**
     * The Cough class is a {@link Action} that makes entity's cough for a specified amount of time
     *
     * @see Action
     */
    public Cough() {
        lifeSpanInSeconds = GameLoop.UPDATES_PER_SECOND;
    }

    @Override
    public void update(State state, MovingEntity entity) {
        lifeSpanInSeconds--;
    }

    @Override
    public boolean isDone() {
        return lifeSpanInSeconds <= 0;
    }

    @Override
    public String getAnimationName() {
        return "cough";
    }
}
