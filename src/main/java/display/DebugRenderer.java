package display;

import core.CollisionBox;
import entity.GameObject;
import entity.humanoid.Humanoid;
import state.State;
import ui.UIText;

import java.awt.*;
import java.util.stream.Collectors;

public class DebugRenderer {

    /**
     * Renders debug info
     *
     * @param state    The state
     * @param graphics The graphics
     * @see Graphics
     * @see State
     */
    public void render(State state, Graphics graphics) {
        Camera camera = state.getCamera();

        state.getGameObjects().stream()
                .filter(camera::isInView)
                .map(GameObject::getCollisionBox)
                .forEach(collisionBox -> drawCollisionBox(collisionBox, graphics, camera));

        drawEffects(state, graphics);
    }

    private void drawEffects(State state, Graphics graphics) {
        Camera camera = state.getCamera();
        state.getGameObjectsOfClass(Humanoid.class).stream()
                .forEach(humanoid -> {
                    UIText effectsText = new UIText(
                            humanoid.getEffects().stream().map(effect -> effect.getClass().getSimpleName()).collect(Collectors.joining(","))
                    );
                    effectsText.update(state);

                    graphics.drawImage(
                            effectsText.getSprite(),
                            humanoid.getPosition().intX() - camera.getPosition().intX(),
                            humanoid.getPosition().intY() - camera.getPosition().intY(),
                            null
                    );
                });
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
