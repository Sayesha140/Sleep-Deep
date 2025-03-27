import java.util.List;

public interface IGoalStorageManager {

    void saveGoals(List<Goal>goals);
    List<Goal> loadGoals();
}