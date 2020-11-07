package entity;

import controller.Controller;
import entity.effect.Caffeinated;
import gfx.SpriteLibrary;

public class Player extends MovingEntity {

    /**
     * The player class manages the player
     * @param controller The controller
     * @param spriteLibrary The sprite library
     *
     * @see Controller
     * @see SpriteLibrary
     */
    public Player(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);
        effects.add(new Caffeinated());
    }
}
