package entity.humanoid.action;

import core.Motion;
import core.Vector2D;
import entity.humanoid.Humanoid;
import game.GameLoop;
import state.State;

public class WalkInDirection extends Action {

    private int walkTime;
    private final Motion motion;

    /**
     * The WalkInDirection allows a NPC/Humanoid to walk in a specific direction
     * @param direction The direction to walk in
     *
     * @see Vector2D
     */
    public WalkInDirection(Vector2D direction) {
        walkTime = GameLoop.UPDATES_PER_SECOND * 3;
        motion = new Motion(1);
        motion.add(direction);
    }

    @Override
    public void update(State state, Humanoid humanoid) {
        walkTime--;

        humanoid.apply(motion);
    }

    @Override
    public boolean isDone() {
        return walkTime == 0;
    }

    @Override
    public String getAnimationName() {
        return "walk";
    }

    @Override
    public String getSoundName() {
        return null;
    }
}
