package display;

import core.CollisionBox;
import entity.GameObject;
import game.state.State;

import java.awt.*;

public class DebugRenderer {

    /**
     * Renders debug info
     * @param state The state
     * @param graphics The graphics
     *
     * @see Graphics
     * @see State
     */
    public void render(State state, Graphics graphics) {
        Camera camera = state.getCamera();

        state.getGameObjects().stream()
                .filter(camera::isInView)
                .map(GameObject::getCollisionBox)
                .forEach(collisionBox -> drawCollisionBox(collisionBox, graphics, camera));
    }

    private void drawCollisionBox(CollisionBox collisionBox, Graphics graphics, Camera camera) {
        graphics.setColor(Color.red);
        graphics.drawRect(
                (int) collisionBox.getBounds().getX() - camera.getPosition().intX(),
                (int) collisionBox.getBounds().getY() - camera.getPosition().intY(),
                (int) collisionBox.getBounds().getWidth(),
                (int) collisionBox.getBounds().getHeight()
        );
    }
}
