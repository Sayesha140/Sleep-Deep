public class TipReaderFactory {
    public static ITipReader getTipReader(String filePath){
        if (filePath.endsWith(".txt")){
            return new TipTxtFileReader();
        }
        else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }

    }
}
