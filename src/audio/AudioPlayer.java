package audio;

import game.settings.AudioSettings;
import game.settings.GameSettings;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayer {

    private AudioSettings audioSettings;
    private List<AudioClip> audioClips;

    /**
     * The AudioPlayer class manages playing audio
     *
     * @param audioSettings The audio settings
     *
     * @see AudioSettings
     */
    public AudioPlayer(AudioSettings audioSettings) {
        this.audioSettings = audioSettings;
        audioClips = new ArrayList<>();
    }

    public void update() {
        audioClips.forEach(audioClip -> audioClip.update(audioSettings));

        List.copyOf(audioClips).forEach(audioClip -> {
            if (audioClip.hasFinishedPlaying()) {
                audioClip.cleanUp();
                audioClips.remove(audioClip);
            }
        });
    }

    /**
     * Play music
     * @param fileName The name of the music file
     */
    public void playMusic(String fileName) {
        final Clip clip = getClip(fileName);
        final MusicClip musicClip = new MusicClip(clip);
        musicClip.setVolume(audioSettings);
        audioClips.add(musicClip);
    }

    /**
     * Play a sound
     * @param fileName The name of the sound file
     */
    public void playSound(String fileName) {
        final Clip clip = getClip(fileName);
        final SoundClip soundClip = new SoundClip(clip);
        soundClip.setVolume(audioSettings);
        audioClips.add(soundClip);
    }


    private Clip getClip(String fileName) {
        final URL soundFile = AudioPlayer.class.getResource("/sounds/" + fileName);
        try(AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)) {
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setMicrosecondPosition(0);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        return null;
    }
}
