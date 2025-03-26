public class SleepLogFileFactory {
    public static ISleepLogStorageManager getLogFile(String filePath){
        if (filePath.endsWith(".csv")){
            return new SleepLogCsvFileManager();
        }
        else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }
    }
}