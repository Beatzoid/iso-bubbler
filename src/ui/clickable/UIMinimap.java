package ui.clickable;

import core.Position;
import core.Size;
import display.Camera;
import game.Game;
import gfx.ImageUtils;
import map.GameMap;
import state.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIMinimap extends UIClickable{

    private double ratio;
    private int pixelsPerGrid;
    private Position pixelOffset;
    private Rectangle cameraViewBounds;
    private BufferedImage mapImage;
    private Color color;

    /**
     * The UIMinimap class manages the Minimap
     *
     * @param gameMap The GameMap
     *
     * @see GameMap
     */
    public UIMinimap(GameMap gameMap) {
        size = new Size(128, 128);
        cameraViewBounds = new Rectangle(0, 0, 0, 0);
        color = Color.GRAY;

        calculateRatio(gameMap);
        generateMap(gameMap);
    }

    @Override
    public void update(State state) {
        super.update(state);

        // Every 0.25 seconds update the Minimap
        if (state.getTime().secondsDividableBy(0.25)) {
            generateMap(state.getGameMap());
        }

        Camera camera = state.getCamera();
        cameraViewBounds = new Rectangle(
                (int) (camera.getPosition().getX() * ratio + pixelOffset.intX()),
                (int) (camera.getPosition().getY() * ratio + pixelOffset.intY()),
                (int) (camera.getSize().getWidth() * ratio),
                (int) (camera.getSize().getHeight() * ratio)
        );

        color = Color.GRAY;
        if (hasFocus) {
            color = Color.WHITE;
        }
    }

    private void generateMap(GameMap gameMap) {
        mapImage = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D graphics = mapImage.createGraphics();

        for (int x = 0; x < gameMap.getTiles().length; x++) {
            for (int y = 0; y < gameMap.getTiles()[0].length; y++) {
                graphics.drawImage(
                        gameMap.getTiles()[x][y].getSprite().getScaledInstance(pixelsPerGrid, pixelsPerGrid, Image.SCALE_AREA_AVERAGING),
                        x * pixelsPerGrid + pixelOffset.intX(),
                        y * pixelsPerGrid + pixelOffset.intY(),
                        null
                );
            }
        }
    }

    private void calculateRatio(GameMap gameMap) {
        ratio = Math.min(
                size.getWidth() / (double) gameMap.getWidth(),
                size.getHeight() / (double) gameMap.getHeight()
        );

        pixelsPerGrid = (int) Math.round(Game.SPRITE_SIZE * ratio);

        pixelOffset = new Position(
                (size.getWidth() - gameMap.getTiles().length * pixelsPerGrid) / 2,
                (size.getHeight() - gameMap.getTiles()[0].length * pixelsPerGrid) / 2
        );
    }

    @Override
    protected void onFocus(State state) {}

    @Override
    public void onDrag(State state) {
        Position mousePosition = Position.copyOf(state.getInput().getMousePosition());
        mousePosition.subtract(absolutePosition);
        mousePosition.subtract(pixelOffset);

        state.getCamera().setPosition(
                new Position(
                        // Center the position around the place where you click
                        mousePosition.getX() / ratio - cameraViewBounds.getSize().getWidth() / ratio / 2,
                        mousePosition.getY() / ratio - cameraViewBounds.getSize().getHeight() / ratio / 2
                )
        );
    }

    @Override
    public void onClick(State state) {}

    @Override
    public Image getSprite() {
        BufferedImage sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D graphics = sprite.createGraphics();

        graphics.drawImage(mapImage, 0, 0, null);

        graphics.setColor(color);
        graphics.drawRect(0, 0, size.getWidth() - 1, size.getHeight() - 1);

        graphics.draw(cameraViewBounds);

        graphics.dispose();
        return sprite;
    }
}
