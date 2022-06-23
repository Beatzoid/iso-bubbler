package entity.humanoid;

import controller.EntityController;
import core.Position;
import core.Size;
import entity.GameObject;
import entity.MovingEntity;
import entity.humanoid.action.Action;
import entity.humanoid.effect.Effect;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Humanoid extends MovingEntity {

    protected final List<Effect> effects;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    protected Optional<Action> action;

    private final static List<String> availableCharacters = new ArrayList<>(List.of("dave", "matt", "melissa", "roger"));

    /**
     * The Humanoid class manages all human entities
     *
     * @param entityController The controller
     * @param spriteLibrary    The SpriteLibrary
     * @see GameObject
     * @see EntityController
     * @see SpriteLibrary
     */
    public Humanoid(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);

        effects = new ArrayList<>();
        action = Optional.empty();

        this.animationManager = new AnimationManager(spriteLibrary.getSpriteSet(getRandomCharacter()));

        this.collisionBoxSize = new Size(16, 28);
        this.renderOffset = new Position(size.getWidth() / 2, size.getHeight() - 12);
        this.collisionBoxOffset = new Position(collisionBoxSize.getWidth() / 2, collisionBoxSize.getHeight());
    }

    private String getRandomCharacter() {
        Collections.shuffle(availableCharacters);
        return availableCharacters.get(0);
    }

    /**
     * Update the Humanoid
     *
     * @param state The state
     */
    @Override
    public void update(State state) {
        super.update(state);
        handleAction(state);
        effects.forEach(effect -> effect.update(state, this));

        cleanup();
    }

    private void cleanup() {
        List.copyOf(effects).stream()
                .filter(Effect::shouldDelete)
                .forEach(effects::remove);

        if (action.isPresent() && action.get().isDone()) {
            action = Optional.empty();
        }
    }

    @Override
    protected String decideAnimation() {
        if (action.isPresent()) {
            return action.get().getAnimationName();
        } else if (motion.isMoving()) {
            return "walk";
        }

        return "stand";
    }

    private void handleAction(State state) {
        action.ifPresent(value -> {
            value.update(state, this);
            value.playSound(state.getAudioPlayer());
        });
    }

    protected void handleMotion() {
        if (action.isPresent()) {
            motion.stop(true, true);
        }
    }

    /**
     * Perform a action
     *
     * @param action The action to perform
     */
    public void perform(Action action) {
        if (this.action.isPresent() && !this.action.get().isInterruptable()) return;
        this.action = Optional.of(action);
    }

    /**
     * Add a Effect
     *
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
     * Get whether or not a moving entity is affected by a effect.
     * The reason this is "clazz" and not "class" is because "class" is a Java keyword, and will throw an error if we try to use it as a parameter.
     *
     * @param clazz The effect to check.
     */
    public boolean isAffectedBy(Class<?> clazz) {
        return effects.stream()
                .anyMatch(clazz::isInstance);
    }

    @Override
    protected void handleCollision(GameObject other) {
    }

    /**
     * Get the effects on the Humanoid
     */
    public List<Effect> getEffects() {
        return effects;
    }
}
