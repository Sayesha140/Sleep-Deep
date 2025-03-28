import java.time.LocalDate;
import java.util.List;

public interface IGoalManager {

    void addGoal(LocalDate date, double targetHours);
    void editGoal(int id, LocalDate date, double targetHours);
    void deleteGoal(int id);
    List<String[]> viewGoals();
    Goal getGoalForDate(LocalDate date);
    boolean goalExists(LocalDate date) ;
    boolean goalIdExist(int id);
    List<Goal> getGoals();
}
