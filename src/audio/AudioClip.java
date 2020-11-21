package audio;

import game.settings.GameSettings;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public abstract class AudioClip {

    private final Clip clip;

    /**
     * The AudioClip class manages audio clip's
     * @param clip The audio clip
     */
    public AudioClip(Clip clip) {
        this.clip = clip;
        clip.start();
    }

    public void update(GameSettings gameSettings) {
         final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
         control.setValue(getVolume(gameSettings));
    }

    protected abstract float getVolume(GameSettings gameSettings);

    /**
     * Get whether or not the AudioClip has finished playing
     */
    public boolean hasFinishedPlaying() {
        return !clip.isRunning();
    }

    /**
     * Cleanup the AudioClip
     */
    public void cleanUp() {
        clip.close();
    }
}
