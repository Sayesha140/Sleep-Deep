import java.time.LocalDate;
import java.util.List;

public interface IGoalManager {

    public void addGoal(LocalDate date,double targetHours);
    public void editGoal(int id,LocalDate date,double targetHours);
    public void deleteGoal(int id);
    public List<String[]> viewGoals();
    boolean goalExists(LocalDate date) ;
    boolean goalIdExist(int id);
    public List<Goal> getGoals();
}
