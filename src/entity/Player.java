package entity;

import controller.EntityController;
import gfx.SpriteLibrary;

public class Player extends MovingEntity {

    /**
     * The player class manages the player
     * @param entityController The controller
     * @param spriteLibrary The sprite library
     *
     * @see EntityController
     * @see SpriteLibrary
     */
    public Player(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);
    }

    @Override
    protected void handleCollision(GameObject other) {
        if (other instanceof NPC) {
            NPC npc = (NPC) other;
            npc.clearEffects();
        }
    }
}
