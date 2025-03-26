import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ISleepLogService {
    void addSleepLog(LocalDate date, LocalTime startTime, LocalTime endTime, double sleepDuration);
    void editLogs(int id, LocalDate date, LocalTime startTime, LocalTime endTime, double sleepDuration);
    void deleteLog(int id);
    List<String[]> viewLogs();
    boolean logIdExists(int id);
}
