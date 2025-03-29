import java.time.LocalDate;

public interface ISleepLogAnalyzer {
    double calculateTotalSleepDurationByDate(LocalDate date);
    double calculateTotalTimeInBedByDate(LocalDate date);
    double calculateSleepDebt(SleepTimeRecommendationStrategy recommendationStrategy,double totalSleepDuration);
}
