import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GoalManager implements IGoalManager{

    private final List<Goal>goals;
    private final IGoalStorageManager storageManager;

    public GoalManager(IGoalStorageManager storageManager){
        this.storageManager=storageManager;
        this.goals=storageManager.loadGoals();
    }


    @Override
    public List<Goal> getGoals() {
        return Collections.unmodifiableList(goals);
    }

    @Override
    public void addGoal(LocalDate date, double targetHours) {
            int id=goals.size()+1;
            Goal goal=new Goal(id,date,targetHours);
            goals.add(goal);
            storageManager.saveGoals(goals);
    }

    @Override
    public void editGoal(int id, LocalDate date, double targetHours)  {
            Goal editedGoal = new Goal(id, date, targetHours);
            goals.set(id - 1, editedGoal);
            storageManager.saveGoals(goals);
    }

    @Override
    public void deleteGoal(int id){
            goals.remove(id - 1);
            reorganizeId();
            storageManager.saveGoals(goals);
    }

    private void reorganizeId(){
        for (int i=0;i<goals.size();i++){
            goals.get(i).setId(i+1);
        }
    }

    @Override
    public List<String[]> viewGoals() {

        goals.sort(Comparator.comparing(Goal::getDate));
        reorganizeId();

        List<String[]> goalDataList = new ArrayList<>();

        for (Goal goal : goals) {
            goalDataList.add(new String[]{
                    String.valueOf(goal.getId()),
                    goal.getDate().toString(),
                    String.format("%.2f", goal.getTargetHours()) + " hours"
            });
        }

        return goalDataList;
    }

    @Override
    public Goal getGoalForDate(LocalDate date){
        for (Goal goal:goals){
            if (goal.getDate().equals(date)){
                return goal;
            }
        }
        return null;
    }


    @Override
    public boolean goalExists(LocalDate date) {
        return goals.stream().anyMatch(goal -> goal.getDate().equals(date));
    }

    @Override
    public boolean goalIdExist(int id){
        return getGoals().stream().anyMatch(goal -> goal.getId() == id);
    }
}
