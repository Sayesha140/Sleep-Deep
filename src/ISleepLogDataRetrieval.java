import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ISleepLogDataRetrieval {
    Map<LocalDate, List<SleepLog>> groupLogsByDate();
    List<SleepLog> getLogsForDate(LocalDate date);
    List<SleepLog> getSleepLogs();
}
