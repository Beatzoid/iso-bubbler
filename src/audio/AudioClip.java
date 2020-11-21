package audio;

import game.settings.AudioSettings;
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

    public void update(AudioSettings audioSettings) {
        setVolume(audioSettings);
    }

    /**
     * Set the volume
     * @param audioSettings The audio settings
     *
     * @see AudioSettings
     */
    void setVolume(AudioSettings audioSettings) {
        final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = control.getMaximum() - control.getMinimum();
        float gain = (range * getVolume(audioSettings)) + control.getMinimum();

        control.setValue(gain);
    }

    protected abstract float getVolume(AudioSettings audioSettings);

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
