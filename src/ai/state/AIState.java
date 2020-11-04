package ai.state;

import ai.AITransition;
import entity.NPC;
import game.state.State;

public abstract class AIState {

    private AITransition transition;

    public AIState() {
        this.transition = initializeTransition();
    }

    protected abstract AITransition initializeTransition();
    public abstract void update(State state, NPC currentCharacter);

    public boolean shouldTransition(State state, NPC currentCharacter) {
        return transition.shouldTransition(state, currentCharacter);
    }

    public String getNextState() {
        return transition.getNextState();
    }
}
