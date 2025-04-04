import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgressTracker implements IReportGenerator{

    IGoalManager goalManager;
    ISleepLogAnalyzer analyzer;

    public ProgressTracker(IGoalManager goalManager,ISleepLogAnalyzer analyzer){
        this.goalManager=goalManager;
        this.analyzer=analyzer;
    }

    @Override

    public List<Map<String, String>> generateReport() {

        List<Map<String, String>> reportData = new ArrayList<>();
        List<Goal> goals = goalManager.getGoals();

        for (Goal goal : goals) {
            LocalDate date = goal.getDate();
            double targetHours = goal.getTargetHours();
            double totalSleptHours = analyzer.calculateTotalSleepDurationByDate(date);
            String verdict = getVerdict(totalSleptHours, targetHours);

            Map<String, String> entry = new HashMap<>();
            entry.put("Date", date.toString());
            entry.put("Target Hours", String.format("%.2f", targetHours));
            entry.put("Hours Slept", String.format("%.2f", totalSleptHours));
            entry.put("Status", verdict);

            reportData.add(entry);
        }

        return reportData;
    }


    private String getVerdict(double totalSleptHours,double targetHours){

        String verdict;

        if (totalSleptHours==0){
            verdict="----";
        }
        else if (totalSleptHours>targetHours+2) {
            verdict="Overslept";
        }
        else if(totalSleptHours>=targetHours){
            verdict="Goal Met";
        }
        else {
            verdict="Missed Goal";
        }
        return verdict;
    }

}
