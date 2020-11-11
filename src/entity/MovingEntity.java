package entity;

import controller.EntityController;
import core.*;
import entity.action.Action;
import entity.effect.Effect;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class MovingEntity extends GameObject {

    protected EntityController entityController;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;
    protected List<Effect> effects;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    protected Optional<Action> action;

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
        this.animationManager = new AnimationManager(spriteLibrary.getUnit("matt"));
        effects = new ArrayList<>();
        action = Optional.empty();
        this.collisionBoxSize = new Size(16, 28);
        this.renderOffset = new Position(size.getWidth() / 2, size.getHeight() - 12);
        this.collisionBoxOffset = new Position(collisionBoxSize.getWidth() / 2, collisionBoxSize.getHeight());
    }

    /**
     * Update the MovingEntity
     * @param state The state
     */
    @Override
    public void update(State state) {
        handleAction(state);
        handleMotion();
        animationManager.update(direction);
        effects.forEach(effect -> effect.update(state, this));

        handleCollisions(state);
        manageDirection();
        decideAnimation();

        position.apply(motion);

        cleanup();
    }

    private void handleCollisions(State state) {
        state.getCollidingGameObjects(this).forEach(this::handleCollision);
    }

    protected abstract void handleCollision(GameObject other);

    private void handleMotion() {
        if (action.isEmpty()) {
            motion.update(entityController);
        } else {
            motion.stop(true, true);
        }
    }

    @SuppressWarnings("OptionalIsPresent")
    private void handleAction(State state) {
        if (action.isPresent()) {
            action.get().update(state, this);
        }
    }

    private void cleanup() {
        List.copyOf(effects).stream()
                .filter(Effect::shouldDelete)
                .forEach(effects::remove);

        if(action.isPresent() && action.get().isDone()) {
            action = Optional.empty();
        }
    }

    private void decideAnimation() {
        if (action.isPresent()) {
            animationManager.playAnimation(action.get().getAnimationName());
        } else if (motion.isMoving()) {
            animationManager.playAnimation("walk");
        } else {
            animationManager.playAnimation("stand");
        }
    }

    private void manageDirection() {
        if (motion.isMoving()) {
            this.direction = Direction.fromMotion(motion);
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
     * Multiply the speed
     * @param multiplier How much to multiply the speed by
     */
    @SuppressWarnings("unused")
    public void multiplySpeed(double multiplier) {
        motion.multiply(multiplier);
    }

    /**
     * Perform a action
     * @param action The action to perform
     */
    public void perform(Action action) {
        this.action = Optional.of(action);
    }

    /**
     * Add a Effect
     * @param effect The Effect to add
     */
    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    /**
     * Clear the Effects of an Entity
     */
    protected void clearEffects() {
        effects.clear();
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
     * Get whether or not a moving entity is affected by a effect.
     * The reason this is "clazz" and not "class" is because "class" is a Java keyword, and will throw an error if we try to use it as a parameter.
     * @param clazz The effect to check.
     */
    public boolean isAffectedBy(Class<?> clazz) {
        return effects.stream()
                .anyMatch(clazz::isInstance);
    }
}
