import java.time.LocalDate;

public interface ISleepLogAnalyzer {
    double calculateTotalSleepDurationByDate(LocalDate date);
    double calculateTotalTimeInBedByDate(LocalDate date);
}
