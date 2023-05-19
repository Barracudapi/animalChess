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

    public static void main(String[] args) {
        Path filePath = Path.of("C:\\Users\\harro\\OneDrive\\Documents\\GitHub\\animalChess\\game\\saved-games\\Harrold.txt");
        String fileContent = readSavedFile(filePath);
        if(fileContent != null){
            System.out.println(fileContent);
        }
    }
}
