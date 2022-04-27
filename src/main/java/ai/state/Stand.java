package ai.state;

import ai.AITransition;
import entity.NPC;
import state.State;

public class Stand extends AIState {

    private int updatesAlive;

    /**
     * Initialize the transition
     */
    @Override
    protected AITransition initializeTransition() {
        return new AITransition("wander", ((state, currentCharacter) -> updatesAlive >= state.getTime().getUpdatesFromSeconds(3)));
    }

    /**
     * Update the state
     * @param state The state
     * @param currentCharacter The character
     *
     * @see State
     * @see NPC
     */
    @Override
    public void update(State state, NPC currentCharacter) {
        updatesAlive++;
    }
}
