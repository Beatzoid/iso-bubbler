package entity;

import ai.AIManager;
import controller.Controller;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

public class NPC extends MovingEntity {

    private AIManager aiManager;

    /**
     * The NPC class manages all NPC's
     * @param controller The controller
     * @param spriteLibrary The sprite library
     *
     * @see Controller
     * @see SpriteLibrary
     */
    public NPC(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);
        animationManager = new AnimationManager(spriteLibrary.getUnit("dave"));
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
            motion.stop();
        }
    }
}
