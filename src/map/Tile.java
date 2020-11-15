package map;

import gfx.SpriteLibrary;

import java.awt.*;

public class Tile {

    private Image sprite;

    /**
     * The Tile class handles loading and managing tiles
     * @param spriteLibrary The spriteLibary to use
     *
     * @see SpriteLibrary
     */
    public Tile(SpriteLibrary spriteLibrary) {
        this.sprite = spriteLibrary.getImage("woodfloor");
    }

    /**
     * Get a sprite
     */
    public Image getSprite() {
        return sprite;
    }
}
