package entity.humanoid.action;

import core.CollisionBox;
import core.Position;
import core.Size;
import entity.humanoid.Humanoid;
import entity.humanoid.effect.Sick;
import game.Game;
import game.GameLoop;
import state.State;

public class Cough extends Action {

    private int lifeSpanInSeconds;
    private final Size spreadAreaSize;
    private final double risk;

    /**
     * The Cough class is a Action that makes entity's cough for a specified amount of time
     *
     * @see Action
     */
    public Cough() {
        lifeSpanInSeconds = GameLoop.UPDATES_PER_SECOND;
        spreadAreaSize = new Size(2 * Game.SPRITE_SIZE, 2 * Game.SPRITE_SIZE);
        risk = 0.1;
    }

    @Override
    public void update(State state, Humanoid performer) {
        if (--lifeSpanInSeconds <= 0) {
            Position spreadAreaPosition = new Position(
                    performer.getPosition().getX() - spreadAreaSize.getWidth() / 2d,
                    performer.getPosition().getY() - spreadAreaSize.getHeight() / 2d
            );

            CollisionBox spreadArea = CollisionBox.of(spreadAreaPosition, spreadAreaSize);

            state.getGameObjectsOfClass(Humanoid.class).stream()
                    .filter(humanoid -> humanoid.getCollisionBox().collidesWith(spreadArea))
                    .filter(humanoid -> !humanoid.isAffectedBy(Sick.class))
                    .forEach(humanoid -> {

                        if (Math.random() < risk) {
                            humanoid.addEffect(new Sick());
                        }
                    });
        }
    }

    @Override
    public boolean isDone() {
        return lifeSpanInSeconds <= 0;
    }

    @Override
    public String getAnimationName() {
        return "cough";
    }

    @Override
    public String getSoundName() {
        return null;
    }
}
