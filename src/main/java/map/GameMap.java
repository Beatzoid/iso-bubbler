package map;

import core.Position;
import core.Size;
import display.Camera;
import game.Game;
import gfx.SpriteLibrary;

import java.util.Arrays;

public class GameMap {

    private static final int SAFETY_SPACE = 2;

    private final Tile[][] tiles;

    /**
     * The GameMap class handles the GameMap
     * @param size The size of the GameMap
     * @param spriteLibrary The spriteLibrary to use for the GameLibrary
     *
     * @see Size
     * @see SpriteLibrary
     */
    public GameMap(Size size, SpriteLibrary spriteLibrary) {
        tiles = new Tile[size.getWidth()][size.getHeight()];
        initializeTiles(spriteLibrary);
    }

    private void initializeTiles(SpriteLibrary spriteLibrary) {
        for (Tile[] row : tiles){
            Arrays.fill(row, new Tile(spriteLibrary));
        }
    }

    /**
     * Get the GameMap tiles
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Get the width of the GameMap
     */
    public int getWidth() {
        return tiles.length * Game.SPRITE_SIZE;
    }

    /**
     * Get the height of the GameMap
     */
    public int getHeight() {
        return tiles[0].length * Game.SPRITE_SIZE;
    }

    /**
     * Get a random position on the GameMap
     */
    public Position getRandomPosition() {
        double x = Math.random() * tiles.length * Game.SPRITE_SIZE;
        double y = Math.random() * tiles[0].length * Game.SPRITE_SIZE;

        return new Position(x, y);
    }

    /**
     * Get the viewable starting grid position
     * @param camera The camera to use
     */
    public Position getViewableStartingGridPosition(Camera camera) {
        return new Position(
                Math.max(0, camera.getPosition().getX() / Game.SPRITE_SIZE - SAFETY_SPACE),
                Math.max(0, camera.getPosition().getY() / Game.SPRITE_SIZE - SAFETY_SPACE)
        );
    }

    /**
     * Get the viewable ending grid position
     * @param camera The camera to use
     */
    public Position getViewableEndingGridPosition(Camera camera) {
        return new Position(
                Math.min(tiles.length, camera.getPosition().getX() / Game.SPRITE_SIZE + camera.getSize().getWidth() / Game.SPRITE_SIZE + SAFETY_SPACE),
                Math.min(tiles[0].length, camera.getPosition().getY() / Game.SPRITE_SIZE + camera.getSize().getHeight() / Game.SPRITE_SIZE + SAFETY_SPACE)
        );
    }
}
