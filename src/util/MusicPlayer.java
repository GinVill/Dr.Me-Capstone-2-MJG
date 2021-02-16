package util;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicPlayer {

    private Clip clip;
    private FloatControl controller;
    private ClassLoader cl = MusicPlayer.class.getClassLoader();


    /**
     * This class takes a .wav file and allows music to be applied to your application. Ensure that when using this
     *
     * @param filepath the path from content root is used.
     *                 <p>
     *                 For short clips i.e. books falling, walking, laughter: make sure to use the appropriate method.
     *                 <p>
     *                 The controller field allows for volume adjustment and is best utilized when attached to a GUI slider.
     */


    public MusicPlayer(String filepath) {

        {
            try {

                InputStream musicPath = cl.getResourceAsStream(filepath);
                assert musicPath != null;
                InputStream bufferedIn = new BufferedInputStream(musicPath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                controller = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            } catch (UnsupportedAudioFileException | LineUnavailableException | NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startMusic() {
        clip.start();
    }

    public void pauseMusic() {
        clip.stop();
    }

    public void quitMusic() {
        clip.close();

    }

    public void playSoundEffect() {
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void stopSoundEffect() {
        clip.setMicrosecondPosition(0);
        clip.stop();
    }

    public float getVolume() {
        return controller.getValue();
    }

    public void setVolume(float volume) {
        controller.setValue(volume);
    }

}
