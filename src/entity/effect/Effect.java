package entity.effect;

import entity.MovingEntity;
import game.state.State;

public abstract class Effect {

    private int lifeSpanInUpdates;

    public Effect(int lifeSpanInUpdates) {
        this.lifeSpanInUpdates = lifeSpanInUpdates;
    }

    public void update(State state, MovingEntity entity) {
        lifeSpanInUpdates--;
    }

    public boolean shouldDelete() {
        return lifeSpanInUpdates <= 0;
    }
}
