package audio;

import game.settings.GameSettings;

import javax.sound.sampled.Clip;

public class SoundClip extends AudioClip {

    /**
     * The SoundClip class manages sound clip's
     *
     * @param clip The sound clip
     */
    public SoundClip(Clip clip) {
        super(clip);
    }

    @Override
    protected float getVolume(GameSettings gameSettings) {
        return gameSettings.getSoundVolume();
    }
}
