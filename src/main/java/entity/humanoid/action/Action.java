package entity.humanoid.action;

import audio.AudioPlayer;
import entity.MovingEntity;
import entity.humanoid.Humanoid;
import state.State;

/**
 * The Action class manages actions for entities
 */
public abstract class Action {

    protected boolean interruptable;
    protected boolean soundPlaying;

    /**
     * The Action class manages Actions for NPC's and Players
     */
    public Action() {
        interruptable = true;
    }

    /**
     * The update function updates the action
     *
     * @param state    The state
     * @param humanoid The humanoid
     * @see State
     * @see MovingEntity
     */
    public abstract void update(State state, Humanoid humanoid);

    /**
     * Whether or not the action is done
     */
    public abstract boolean isDone();

    /**
     * Get the animation name
     */
    public abstract String getAnimationName();

    /**
     * Get the sound name
     */
    public abstract String getSoundName();

    public void playSound(AudioPlayer audioPlayer) {
        if (!soundPlaying && getSoundName() != null) {
            audioPlayer.playSound(getSoundName());
            soundPlaying = true;
        }
    }

    /**
     * Whether or not the Action is interruptable
     */
    public boolean isInterruptable() {
        return interruptable;
    }
}
