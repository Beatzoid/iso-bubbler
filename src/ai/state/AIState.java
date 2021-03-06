package ai.state;

import ai.AITransition;
import entity.NPC;
import state.State;

public abstract class AIState {

    private final AITransition transition;

    public AIState() {
        this.transition = initializeTransition();
    }
    protected abstract AITransition initializeTransition();
    public abstract void update(State state, NPC currentCharacter);

    public boolean shouldTransition(State state, NPC currentCharacter) {
        return transition.shouldTransition(state, currentCharacter);
    }

    /**
     * Get the next state
     */
    public String getNextState() {
        return transition.getNextState();
    }
}
