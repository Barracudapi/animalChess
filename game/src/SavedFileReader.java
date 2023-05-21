import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SavedFileReader {
    public static String readSavedFile(Path filePath){
        try{
            byte[] bytes = Files.readAllBytes(filePath);
            return new String(bytes);
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
