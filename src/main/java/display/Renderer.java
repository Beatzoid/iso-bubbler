package display;

import core.Position;
import game.Game;
import map.GameMap;
import state.State;

import java.awt.*;

public class Renderer {

    /**
     * Render the GameObjects
     *
     * @param state    The state
     * @param graphics The graphics
     * @see Graphics
     * @see State
     */
    public void render(State state, Graphics graphics) {
        renderMap(state, graphics);
        renderGameObjects(state, graphics);
        renderUI(state, graphics);
    }

    private void renderUI(State state, Graphics graphics) {
        state.getMouseHandler().getPrimaryButtonUI().ifPresent(uiImage -> graphics.drawImage(
                uiImage.getSprite(),
                uiImage.getAbsolutePosition().intX(),
                uiImage.getAbsolutePosition().intY(),
                null
        ));

        state.getUiContainers().forEach(uiContainer -> graphics.drawImage(
                uiContainer.getSprite(),
                uiContainer.getRelativePosition().intX(),
                uiContainer.getRelativePosition().intY(),
                null
        ));
    }

    private void renderGameObjects(State state, Graphics graphics) {
        Camera camera = state.getCamera();
        state.getGameObjects().stream()
                .filter(camera::isInView)
                .forEach(gameObject -> graphics.drawImage(
                        gameObject.getSprite(),
                        gameObject.getRenderPosition(camera).intX(),
                        gameObject.getRenderPosition(camera).intY(),
                        null
                ));
    }

    private void renderMap(State state, Graphics graphics) {
        GameMap map = state.getGameMap();
        Camera camera = state.getCamera();

        Position start = map.getViewableStartingGridPosition(camera);
        Position end = map.getViewableEndingGridPosition(camera);

        for (int x = start.intX(); x < end.intX(); x++) {
            for (int y = start.intY(); y < end.intY(); y++) {
                graphics.drawImage(
                        map.getTiles()[x][y].getSprite(),
                        x * Game.SPRITE_SIZE - camera.getPosition().intX(),
                        y * Game.SPRITE_SIZE - camera.getPosition().intY(),
                        null
                );

                if (state.getGameSettings().getRenderSettings().getShouldRenderGrid().getValue()) {
                    graphics.setColor(Color.GRAY);
                    graphics.drawRect(
                            x * Game.SPRITE_SIZE - camera.getPosition().intX(),
                            y * Game.SPRITE_SIZE - camera.getPosition().intY(),
                            Game.SPRITE_SIZE,
                            Game.SPRITE_SIZE
                    );
                }
            }
        }
    }
}
