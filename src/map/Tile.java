package map;

import game.Game;
import gfx.SpriteLibrary;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    private Image image;
    private Image sprite;
    private int tileIndex;

    /**
     * The Tile class handles loading and managing tiles
     *
     * @param spriteLibrary The spriteLibrary to use
     *
     * @see SpriteLibrary
     */
    public Tile(SpriteLibrary spriteLibrary) {
        this(spriteLibrary, "grass");
    }

    /**
     * The Tile class handles loading and managing tiles
     *
     * @param spriteLibrary The spriteLibrary to use
     * @param tileName The name of the tile to use
     *
     * @see SpriteLibrary
     */
    public Tile(SpriteLibrary spriteLibrary, String tileName) {
        this.image = spriteLibrary.getImage(tileName);
        generateSprite();
    }

    private Tile(Image image, int tileIndex) {
        this.image = image;
        this.tileIndex = tileIndex;
        generateSprite();
    }

    private void generateSprite() {
        sprite = ((BufferedImage)image).getSubimage(
                (tileIndex / (image.getWidth(null) / Game.SPRITE_SIZE)) * Game.SPRITE_SIZE,
                (tileIndex % (image.getWidth(null) / Game.SPRITE_SIZE)) * Game.SPRITE_SIZE,
                Game.SPRITE_SIZE,
                Game.SPRITE_SIZE
        );
    }

    public static Tile copyOf(Tile tile) {
        return new Tile(tile.getImage(), tile.getTileIndex());
    }

    /**
     * Get the tile index
     */
    public int getTileIndex() {
        return tileIndex;
    }

    /**
     * Set the tile index
     * @param tileIndex The new tile index
     */
    public void setTileIndex(int tileIndex) {
        this.tileIndex = tileIndex;
        generateSprite();
    }

    /**
     * Get a sprite
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * Get the image
     */
    public Image getImage() {
        return image;
    }
}
