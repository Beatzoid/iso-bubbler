package entity;

import controller.Controller;
import core.Direction;
import core.Motion;
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

    protected Controller controller;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;
    protected List<Effect> effects;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    protected Optional<Action> action;

    /**
     * The MovingEntity class manages all MovingEntities and extends the GameObject class
     * @param controller The controller
     * @param spriteLibrary The SpriteLibrary
     *
     * @see GameObject
     * @see Controller
     * @see SpriteLibrary
     */
    public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
        super();
        this.controller = controller;
        this.motion = new Motion(2);
        this.direction = Direction.S;
        this.animationManager = new AnimationManager(spriteLibrary.getUnit("matt"));
        effects = new ArrayList<>();
        action = Optional.empty();
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

        manageDirection();
        decideAnimation();

        position.apply(motion);

        cleanup();
    }

    private void handleMotion() {
        if (action.isEmpty()) {
            motion.update(controller);
        } else {
            motion.stop();
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
    public Controller getController() {
        return controller;
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
}
