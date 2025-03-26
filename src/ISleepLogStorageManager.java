import java.util.List;

public interface ISleepLogStorageManager {

    void saveLogs(List<SleepLog>sleepLogs);
    List<SleepLog> loadLogs();
}
