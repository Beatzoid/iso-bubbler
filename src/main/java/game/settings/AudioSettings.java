package game.settings;

public class AudioSettings {

    private float musicVolume;
    private float soundVolume;

    public AudioSettings() {
        musicVolume = 0.7f;
        soundVolume = 0.65f;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }
}
