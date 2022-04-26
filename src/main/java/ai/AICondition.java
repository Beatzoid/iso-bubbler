package ai;

import entity.NPC;
import game.state.State;

public interface AICondition {

    boolean isMet(State state, NPC currentCharacter);
}
