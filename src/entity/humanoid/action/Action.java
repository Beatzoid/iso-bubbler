package entity.humanoid.action;

import entity.MovingEntity;
import entity.humanoid.Humanoid;
import game.state.State;

/**
 * The Action class manages actions for entities
 */
public abstract class Action {

    /**
     * The update function updates the action
     * @param state The state
     * @param humanoid The humanoid
     *
     * @see State
     * @see MovingEntity
     */
    public abstract void update(State state, Humanoid humanoid);

    /**
     * Whether or not the action is done
     */
    public abstract boolean isDone();

    /**
     * Get the animation name
     */
    public abstract String getAnimationName();
}
