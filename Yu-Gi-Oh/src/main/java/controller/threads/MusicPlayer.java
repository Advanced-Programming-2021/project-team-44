package controller.threads;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicPlayer extends Thread {
    public MusicPlayer() {
        super();
        this.setDaemon(true);
        start();
    }

    private Clip clip;
    private boolean isStopped;

    @Override
    public void run() {
        try {
            File audioFile = new File("src/main/resources/static/graphics/audio/All 5 Yu-Gi-Oh Theme Songs.mp3");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            isStopped = false;
            clip.start();
        } catch (Exception e) {
            System.out.println("Music can't be played!");
        }
    }

    public void stopMusic() {
        isStopped = true;
        clip.stop();
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }
}
