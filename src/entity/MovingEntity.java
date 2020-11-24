package entity;

import controller.EntityController;
import core.*;
import state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.awt.*;

public abstract class MovingEntity extends GameObject {

    protected final EntityController entityController;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;

    protected Vector2D directionVector;

    protected Size collisionBoxSize;

    /**
     * The MovingEntity class manages all MovingEntities and extends the GameObject class
     * @param entityController The controller
     * @param spriteLibrary The SpriteLibrary
     *
     * @see GameObject
     * @see EntityController
     * @see SpriteLibrary
     */
    public MovingEntity(EntityController entityController, SpriteLibrary spriteLibrary) {
        super();
        this.entityController = entityController;
        this.motion = new Motion(2);
        this.direction = Direction.S;
        this.directionVector = new Vector2D(0, 0);
        this.animationManager = new AnimationManager(spriteLibrary.getSpriteSet("matt"));
        this.collisionBoxSize = new Size(size.getWidth(), size.getHeight());
    }

    /**
     * Update the MovingEntity
     * @param state The state
     */
    @Override
    public void update(State state) {
        motion.update(entityController);
        handleMotion();
        animationManager.update(direction);

        handleCollisions(state);
        animationManager.playAnimation(decideAnimation());

        apply(motion);
    }

    private void handleCollisions(State state) {
        state.getCollidingGameObjects(this).forEach(this::handleCollision);
    }

    protected abstract void handleCollision(GameObject other);

    protected abstract void handleMotion();

    protected abstract String decideAnimation();

    private void manageDirection(Motion motion) {
        if (motion.isMoving()) {
            this.direction = Direction.fromMotion(motion);
            this.directionVector = motion.getDirection();
        }
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position positionWithMotion = Position.copyOf(getPosition());
        positionWithMotion.apply(motion);
        positionWithMotion.subtract(collisionBoxOffset);

        return new CollisionBox(
            new Rectangle(
                positionWithMotion.intX(),
                positionWithMotion.intY(),
                collisionBoxSize.getWidth(),
                collisionBoxSize.getHeight()
            )
        );
    }

    /**
     * Get the sprite
     */
    @Override
    public Image getSprite() {
        return animationManager.getSprites();
    }

    /**
     * Get the controller
     */
    public EntityController getController() {
        return entityController;
    }

    /**
     * Get whether a Entity will collide with a GameObject on the X axis
     * @param other The GameObject to check
     *
     * @see GameObject
     */
    public boolean willCollideX(GameObject other) {
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithXApplied = Position.copyOf(position);
        positionWithXApplied.applyX(motion);
        positionWithXApplied.subtract(collisionBoxOffset);

        return CollisionBox.of(positionWithXApplied, collisionBoxSize).collidesWith(otherBox);
    }

    /**
     * Get whether a Entity will collide with a GameObject on the Y axis
     * @param other The GameObject to check
     *
     * @see GameObject
     */
    public boolean willCollideY(GameObject other) {
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithYApplied = Position.copyOf(position);
        positionWithYApplied.applyY(motion);
        positionWithYApplied.subtract(collisionBoxOffset);

        return CollisionBox.of(positionWithYApplied, collisionBoxSize).collidesWith(otherBox);
    }

    /**
     * Get if the entity is facing a position
     * @param other The Position to check
     */
    public boolean isFacing(Position other) {
        Vector2D direction = Vector2D.directionBetweenPositions(other, getPosition());
        double dotProduct = Vector2D.dotProduct(direction, directionVector);

        return dotProduct > 0;
    }

    /**
     * Apply a motion to the MovingEntity
     * @param motion The motion to apply
     *
     * @see Motion
     */
    public void apply(Motion motion) {
        manageDirection(motion);
        position.apply(motion);
    }
}
