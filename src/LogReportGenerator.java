import java.time.LocalDate;
import java.util.*;

public class LogReportGenerator implements IReportGenerator {
    private ISleepLogManager logManager;
    private SleepTimeRecommendationStrategy recommendationStrategy;
    private User user;

    public LogReportGenerator(ISleepLogManager logManager, SleepTimeRecommendationStrategy recommendationStrategy,User user) {
        this.logManager = logManager;
        this.recommendationStrategy = recommendationStrategy;
        this.user=user;
    }


    @Override
    public List<Map<String, String>> generateReport(){

        Map<LocalDate, List<SleepLog>> logsByDate =logManager.groupLogsByDate();

        List<Map<String, String>> reportData = new ArrayList<>();

        for (LocalDate date : logsByDate.keySet()) {

            double totalTimeInBed = logManager.calculateTotalTimeInBedByDate(date);
            double totalSleepDuration = logManager.calculateTotalSleepDurationByDate(date);
            double sleepDebt = calculateSleepDebt(recommendationStrategy,totalSleepDuration);
            String sleepQualityComment=getSleepQualityComment(totalSleepDuration,recommendationStrategy,user);


            Map<String, String> reportEntry = new LinkedHashMap<>();
            reportEntry.put("Date", date.toString());
            reportEntry.put("Total Time in Bed", String.format("%.2f hrs", totalTimeInBed));
            reportEntry.put("Total Sleep Duration", String.format("%.2f hrs", totalSleepDuration));
            reportEntry.put("Sleep Debt", String.format("%.2f hrs", sleepDebt));
            reportEntry.put("Comment", sleepQualityComment);

            reportData.add(reportEntry);
        }

        return reportData;

    }


    private String getSleepQualityComment(double actualSleep, SleepTimeRecommendationStrategy recommendationStrategy,User user) {
        if (actualSleep < recommendationStrategy.getMinSleep()) {
            return "Under-slept";
        } else if (actualSleep > recommendationStrategy.getAdjustedMaxSleep(user)) {
            return "Overslept";
        } else {
            return "Ideal sleep";
        }
    }

    private double calculateSleepDebt(SleepTimeRecommendationStrategy recommendationStrategy,double totalSleepDuration){
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