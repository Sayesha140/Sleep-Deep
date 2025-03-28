import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TipTxtFileReader implements ITipReader{

    //private static final String tip_filePath="C:\\Users\\AYESHA\\IdeaProjects\\SleepDeep-Trial\\src\\QuickTips.txt";
    private static final String tip_filePath="QuickTips.txt";

    @Override
    public List<String> loadTips() {
        try {
            String content = Files.readString(Paths.get(tip_filePath));
            return List.of(content.split("---\\s*"));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
