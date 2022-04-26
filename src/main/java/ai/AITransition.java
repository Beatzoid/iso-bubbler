package ai;

import entity.NPC;
import game.state.State;

public class AITransition {

    private String nextState;
    private AICondition condition;

    /**
     * Manages AI transition between states
     *
     * @param nextState The next state
     * @param condition The condition to transition on. If true, it will transition to nextState
     */
    public AITransition(String nextState, AICondition condition) {
        this.nextState = nextState;
        this.condition = condition;
    }

    /**
     * Returns whether the AI should transition
     *
     * @param state The state
     * @param currentCharacter The current character
     */
    public boolean shouldTransition(State state, NPC currentCharacter) {
        return condition.isMet(state, currentCharacter);
    }

    /**
     * Get the next state
     */
    public String getNextState() {
        return nextState;
    }
}
