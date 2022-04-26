package entity;

import controller.NPCController;
import core.Direction;
import core.Vector2D;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import gfx.SpriteSet;

public class Bubble extends MovingEntity {

    private boolean halted;

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

        this.animationManager = new AnimationManager(new SpriteSet(spriteLibrary.getImage("bubble")), false);
    }

    @Override
    protected void handleCollision(GameObject other) {}

    @Override
    protected void handleMotion() {
        if(!halted) {
            motion.add(new Vector2D(0, -0.5));
        }

        halted = false;
        direction = Direction.S;
    }

    @Override
    protected String decideAnimation() {
        return "default";
    }

    /**
     * Halt the Bubble
     */
    public void halt() {
        halted = true;
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
        this.collisionBoxOffset = renderOffset;
        gameObject.parent(this);
    }
}
