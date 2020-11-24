package entity.humanoid.action;

import controller.NPCController;
import entity.Bubble;
import entity.humanoid.Humanoid;
import entity.humanoid.effect.Isolated;
import game.GameLoop;
import state.State;

public class BlowBubble extends Action {

    private int lifeSpanInUpdates;
    private final Humanoid target;
    private Bubble bubble;

    /**
     * Blow a bubble
     * @param target Target
     */
    public BlowBubble(Humanoid target) {
        lifeSpanInUpdates = GameLoop.UPDATES_PER_SECOND;
        this.target = target;
        interruptible = false;
    }

    @Override
    public void update(State state, Humanoid humanoid) {
        lifeSpanInUpdates--;

        if (bubble == null) {
            bubbleTarget(state);
        } else {
            bubble.halt();
        }

        if (isDone()) {
            target.setRenderOrder(6);
            bubble.setRenderOrder(6);
        }
    }

    private void bubbleTarget(State state) {
        target.perform(new Levitate());
        target.addEffect(new Isolated());

        bubble = new Bubble(new NPCController(), state.getSpriteLibrary());
        bubble.insert(target);
        state.spawn(bubble);
    }

    @Override
    public boolean isDone() {
        return lifeSpanInUpdates == 0;
    }

    @Override
    public String getAnimationName() {
        return "blow";
    }

    @Override
    public String getSoundName() {
        return "bubble.wav";
    }
}
