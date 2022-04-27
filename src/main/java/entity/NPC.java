package entity;

import ai.AIManager;
import controller.EntityController;
import entity.humanoid.Humanoid;
import state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

public class NPC extends Humanoid {

    private AIManager aiManager;

    /**
     * The NPC class manages all NPC's
     * @param entityController The controller
     * @param spriteLibrary The sprite library
     *
     * @see EntityController
     * @see SpriteLibrary
     */
    public NPC(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);
        animationManager = new AnimationManager(spriteLibrary.getSpriteSet("dave"));
        aiManager = new AIManager();
    }

    /**
     * Update the NPC
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