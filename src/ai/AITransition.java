package ai;

import entity.NPC;
import game.state.State;

public class AITransition {

    private String nextState;
    private AICondition condition;

    public AITransition(String nextState, AICondition condition) {
        this.nextState = nextState;
        this.condition = condition;
    }

    public boolean shouldTransition(State state, NPC currentCharacter) {
        return condition.isMet(state, currentCharacter);
    }

    public String getNextState() {
        return nextState;
    }
}
