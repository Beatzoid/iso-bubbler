package map;

import gfx.SpriteLibrary;

import java.awt.*;

public class Tile {

    private Image sprite;

    /**
     * The Tile class handles loading and managing tiles
     *
     * @param spriteLibrary The spriteLibrary to use
     * @see SpriteLibrary
     */
    public Tile(SpriteLibrary spriteLibrary) {
        this(spriteLibrary, "woodfloor");
    }

    public Tile(SpriteLibrary spriteLibrary, String tileName) {
        this.sprite = spriteLibrary.getImage(tileName);
    }

    private Tile(Image sprite) {
        this.sprite = sprite;
    }

    public static Tile copyof(Tile tile) {
        return new Tile(tile.getSprite());
    }

    /**
     * Get a sprite
     */
    public Image getSprite() {
        return sprite;
    }
}
