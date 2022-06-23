package ai;

import ai.state.AIState;
import ai.state.Stand;
import ai.state.Wander;
import entity.NPC;
import state.State;

public class AIManager {

    private AIState currentAIState;

    public AIManager() {
        transitionTo("stand");
    }

    /**
     * Update the current AI State with the new state and currentCharacter,
     * then transition to the next state if shouldTransition is true
     *
     * @param state            The current state
     * @param currentCharacter The current character
     * @see State
     * @see NPC
     * @see AIState
     */
    public void update(State state, NPC currentCharacter) {
        currentAIState.update(state, currentCharacter);

        if (currentAIState.shouldTransition(state, currentCharacter)) {
            transitionTo(currentAIState.getNextState());
        }
    }

    /**
     * Transition to a new state
     *
     * @param nextState The next state to transition to
     */
    private void transitionTo(String nextState) {
        switch (nextState) {
            case "wander" -> currentAIState = new Wander();
            case "stand", default -> currentAIState = new Stand();
        }
    }
}
