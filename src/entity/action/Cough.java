package entity.action;

import core.CollisionBox;
import core.Position;
import core.Size;
import entity.MovingEntity;
import entity.effect.Sick;
import game.Game;
import game.GameLoop;
import game.state.State;

public class Cough extends Action {

    private int lifeSpanInSeconds;
    private Size spreadAreaSize;
    private double risk;

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
    public void update(State state, MovingEntity entity) {
        if (--lifeSpanInSeconds <= 0) {
            Position spreadAreaPosition = new Position(
                    entity.getPosition().getX() - spreadAreaSize.getWidth() / 2d,
                    entity.getPosition().getY() - spreadAreaSize.getHeight() / 2d
            );

            CollisionBox spreadArea = CollisionBox.of(spreadAreaPosition, spreadAreaSize);

            state.getGameObjectsOfClass(MovingEntity.class).stream()
                    .filter(movingEntity -> movingEntity.getCollisionBox().collidesWith(spreadArea))
                    .filter(movingEntity -> !movingEntity.isAffectedBy(Sick.class))
                    .forEach(movingEntity -> {

                        if (Math.random() < risk) {
                            movingEntity.addEffect(new Sick());
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
}
