package ai;

import entity.NPC;
import state.State;

public interface AICondition {

    boolean isMet(State state, NPC currentCharacter);
}
