package entity;

import ai.AIManager;
import controller.EntityController;
import core.Motion;
import entity.humanoid.Humanoid;
import gfx.SpriteLibrary;
import state.State;

public class NPC extends Humanoid {

    private AIManager aiManager;

    /**
     * The NPC class manages all NPC's
     *
     * @param entityController The controller
     * @param spriteLibrary    The sprite library
     * @see EntityController
     * @see SpriteLibrary
     */
    public NPC(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);
        aiManager = new AIManager();
        motion = new Motion(Math.random() + 1);
    }

    /**
     * Update the NPC
     *
     * @param state The state
     */
    @Override
    public void update(State state) {
        super.update(state);
        aiManager.update(state, this);
    }

    @Override
    protected void handleCollision(GameObject other) {
        if (other instanceof Player) {
            motion.stop(willCollideX(other), willCollideY(other));
        }
    }
}
