import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface ISleepLogManager extends ISleepLogAnalyzer,ISleepLogService,ISleepLogDataRetrieval{

    /*void addSleepLog(LocalDate date, LocalTime startTime, LocalTime endTime, double sleepDuration);
    void editLogs(int id, LocalDate date, LocalTime startTime, LocalTime endTime, double sleepDuration);
    void deleteLog(int id);
    List<String[]> viewLogs();
    Map<LocalDate, List<SleepLog>> groupLogsByDate();
    List<SleepLog> getLogsForDate(LocalDate date);
    double calculateTotalSleepDurationByDate(LocalDate date);
    double calculateTotalTimeInBedByDate(LocalDate date);
    boolean logIdExists(int id);
    List<SleepLog> getSleepLogs();*/

}
