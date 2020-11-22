package game.settings;

public class AudioSettings {

    private float musicVolume;
    private float soundVolume;

    /**
     * The AudioSettings class manages audio settings
     */
    public AudioSettings() {
        musicVolume = 0.85f;
        soundVolume = 0.80f;
    }

    /**
     * Get the music volume
     */
    public float getMusicVolume() {
        return musicVolume;
    }

    /**
     * Set the music volume
     * @param musicVolume The new music volume
     */
    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    /**
     * Get the sound volume
     */
    public float getSoundVolume() {
        return soundVolume;
    }

    /**
     * Set the sound volume
     * @param soundVolume The new sound volume
     */
    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }
}
