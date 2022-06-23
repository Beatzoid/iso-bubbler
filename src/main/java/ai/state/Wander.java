package ai.state;

import ai.AITransition;
import controller.NPCController;
import core.Position;
import entity.NPC;
import state.State;

import java.util.ArrayList;
import java.util.List;

public class Wander extends AIState {

    private final List<Position> targets;

    public Wander() {
        super();
        targets = new ArrayList<>();
    }

    /**
     * Initialize the transition
     */
    @Override
    protected AITransition initializeTransition() {
        return new AITransition("stand", ((state, currentCharacter) -> arrived(currentCharacter)));
    }

    /**
     * Update the transition
     * @param state The state
     * @param currentCharacter The character
     *
     * @see State
     * @see NPC
     */
    @Override
    public void update(State state, NPC currentCharacter) {
        if (targets.isEmpty()) {
            targets.add(state.getRandomPosition());
        }

        NPCController controller = (NPCController) currentCharacter.getController();
        controller.moveToTarget(targets.get(0), currentCharacter.getPosition());

        if (arrived(currentCharacter)) {
            controller.stop();
        }
    }

    /**
     *
     * @param currentCharacter The character
     * @return Whether or not the NPC has arrived at the specified location
     */
    private boolean arrived(NPC currentCharacter) {
        return currentCharacter.getPosition().isInRangeOf(targets.get(0));
    }
}
