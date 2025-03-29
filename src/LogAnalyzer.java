import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class LogAnalyzer implements ISleepLogAnalyzer{

    ISleepLogManager logManager;

    public LogAnalyzer(ISleepLogManager logManager){
        this.logManager=logManager;
    }

    @Override
    public double calculateTotalSleepDurationByDate(LocalDate date) {
        double totalSleep=0;
        List<SleepLog> logs=logManager.getLogsForDate(date);

        for (SleepLog log:logs){
            totalSleep+=log.getSleepDuration();
        }
        return totalSleep;
    }

    @Override
    public double calculateTotalTimeInBedByDate(LocalDate date) {
        double totalTimeInBed = 0;
        List<SleepLog> logs=logManager.getLogsForDate(date);

        for (SleepLog log : logs) {
            Duration duration = Duration.between(log.getStartTime(), log.getEndTime());

            if (duration.isNegative()) {
                duration = duration.plusHours(24);
            }

            totalTimeInBed += duration.toMinutes() / 60.0;
        }

        return totalTimeInBed;
    }

    public double calculateSleepDebt(SleepTimeRecommendationStrategy recommendationStrategy,double totalSleepDuration){
        double sleepDebt;

        if(totalSleepDuration<recommendationStrategy.getMinSleep()){
            sleepDebt=recommendationStrategy.getMinSleep()-totalSleepDuration;
        }
        else {
            sleepDebt=0.0;
        }
        return sleepDebt;
    }
}
