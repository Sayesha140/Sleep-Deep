public class UserFileFactory {

    public static UserFileManager getUserFile(String filePath){

        if (filePath.endsWith(".csv")){
            return new UserCSVFileManager();
        }
        else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }

    }
}
