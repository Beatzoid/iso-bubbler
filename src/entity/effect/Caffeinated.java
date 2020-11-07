package entity.effect;

import entity.MovingEntity;
import game.GameLoop;
import game.state.State;

public class Caffeinated extends Effect {

    private double speedMultiplier;

    public Caffeinated() {
        super(GameLoop.UPDATES_PER_SECOND * 5);
        speedMultiplier = 2.5;
    }

    @Override
    public void update(State state, MovingEntity entity) {
        super.update(state, entity);

        entity.multiplySpeed(speedMultiplier);
    }
}
