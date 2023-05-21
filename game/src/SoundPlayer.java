import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer{
    private Clip clip;
    public void loadSound(String filePath){
        try{
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }

    void playSound(){
        if(clip != null){
            clip.setFramePosition(0);
            clip.start();
        }
    }
}
