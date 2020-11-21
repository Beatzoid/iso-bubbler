package audio;

import game.settings.AudioSettings;

import javax.sound.sampled.Clip;

public class MusicClip extends AudioClip {

    /**
     * The MusicClip class manages Music clips
     *
     * @param clip The music clip
     */
    public MusicClip(Clip clip) {
        super(clip);
    }

    @Override
    protected float getVolume(AudioSettings audioSettings) {
        return audioSettings.getMusicVolume();
    }
}
