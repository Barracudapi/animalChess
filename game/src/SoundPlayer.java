import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundPlayer {
    private Clip clip;
    void loadSound(String filePath){
        try{
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    void playSound(){
        if(clip != null){
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public static void main(String[] args) {

    }
}
