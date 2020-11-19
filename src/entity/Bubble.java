package entity;

import controller.NPCController;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import gfx.SpriteSet;

public class Bubble extends MovingEntity {

    private NPCController controller;

    /**
     * The Bubble class manages bubbles
     *
     * @param npcController The controller
     * @param spriteLibrary The SpriteLibrary
     * @see GameObject
     * @see NPCController
     * @see SpriteLibrary
     */
    public Bubble(NPCController npcController, SpriteLibrary spriteLibrary) {
        super(npcController, spriteLibrary);
        this.controller = npcController;

        this.animationManager = new AnimationManager(new SpriteSet(spriteLibrary.getImage("bubble")), false);
    }

    @Override
    protected void handleCollision(GameObject other) {}

    @Override
    protected void handleMotion() {
        motion.update(controller);
    }

    @Override
    protected String decideAnimation() {
        return "default";
    }

    /**
     * Insert a GameObject into the bubble
     * @param gameObject The GameObject to insert
     *
     * @see GameObject
     */
    public void insert(GameObject gameObject) {
        this.position = gameObject.getPosition();
        this.renderOffset = gameObject.getRenderOffset();
        gameObject.parent(this);
    }
}
