public class GoalFileFactory {
    public static IGoalStorageManager getGoalFile(String filePath){
        if (filePath.endsWith(".csv")){
            return new GoalCsvFileManager();
        }
        else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }
    }
}
