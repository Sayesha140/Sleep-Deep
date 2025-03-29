import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GoalCsvFileManager implements IGoalStorageManager{

    private final String GOAL_FILE_PATH="goals.csv";

    @Override
    public void saveGoals(List<Goal> goals) {

        try(BufferedWriter writer=new BufferedWriter(new FileWriter(GOAL_FILE_PATH))) {

            for (Goal goal:goals){
                writer.write(goal.getId()+","+goal.getDate()+","+goal.getTargetHours());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving goal");
        }

    }

    @Override
    public List<Goal> loadGoals() {

        File file=new File("goals.csv");

        if (!file.exists()){
            return new ArrayList<>();
        }

        List<Goal> goals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(GOAL_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.isBlank()){
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id=Integer.parseInt(parts[0]);
                    LocalDate date=LocalDate.parse(parts[1]);
                    double targetHours = Double.parseDouble(parts[2]);
                    goals.add(new Goal(id,date, targetHours));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No goals set for now.");
        } catch (IOException e) {
            System.err.println("Error loading goals.");
        }
        return goals;
    }
}