import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GoalUI {
    private IGoalManager goalManager;
    private IReportGenerator progressTracker;
    private List<Map<String, String>> reportData;
    Scanner scanner;

    public GoalUI(IGoalManager goalManager,IReportGenerator progressTracker,Scanner scanner){
        this.goalManager=goalManager;
        this.progressTracker=progressTracker;
        this.scanner=scanner;
    }

    public void addGoal(){
        System.out.println();
        System.out.println("\n                                                                          ══════════════════════════════");
        System.out.println("                                                                                  Set a Sleep Goal");
        System.out.println("                                                                          ══════════════════════════════");


        for(int i=0;i<5;i++){
            System.out.println();
        }

        LocalDate date = InputValidator.getValidDate(Color.SOFT_LAVENDER+"\nEnter the date you want to set goal for (yyyy-MM-dd): "+Color.RESET,scanner);

        if (goalManager.goalExists(date)){
            System.out.println(Color.RED+"\nGoal already exists for "+date+Color.RESET);
            for(int i=0;i<10;i++){
                System.out.println();
            }
            UtilMethods.waitForEnter(scanner);
            return;
        }

        System.out.println();
        double targetHours=InputValidator.getValidSleepDuration(Color.SOFT_LAVENDER+"\nEnter target sleep time (in hr): "+Color.RESET,scanner);

        goalManager.addGoal(date,targetHours);
        for(int i=0;i<5;i++){
            System.out.println();
        }
        System.out.println(Color.GREEN+"\nGoal for " +date+ " with target " + targetHours + " hrs added successfully."+Color.RESET);

        for(int i=0;i<10;i++){
            System.out.println();
        }
        UtilMethods.waitForEnter(scanner);
    }

    public void editGoal(){
        if (goalManager.getGoals().isEmpty()){
            System.out.println(Color.RED+"\n                                                                     No goals available for now."+Color.RESET);
            for(int i=0;i<6;i++){
                System.out.println();
            }
            UtilMethods.waitForEnter(scanner);
        }
        else {
            viewGoals();
            System.out.println();
                while (true){

                        try {
                            System.out.println();
                            System.out.print(Color.SOFT_LAVENDER+"\nEnter the ID of the goal you want to edit: "+Color.RESET);

                            String input=scanner.nextLine().trim();
                            int id=Integer.parseInt(input);

                            if (!goalManager.goalIdExist(id)){
                                System.out.println("\nInvalid goal ID! Please enter a valid ID.");
                                continue;
                            }

                            Goal goalToEdit = null;
                            for (Goal goal : goalManager.getGoals()) {
                                if (goal.getId() == id) {
                                    goalToEdit = goal;
                                    break;
                                }
                            }

                            System.out.println();
                            LocalDate date = InputValidator.getValidDate(Color.SOFT_LAVENDER+"\nEnter new date (yyyy-MM-dd):"+Color.RESET,scanner);

                            if (goalToEdit != null && !goalToEdit.getDate().equals(date) && goalManager.goalExists(date)){
                                System.out.println(Color.RED+"\nGoal already exists for "+date+Color.RESET);
                                for(int i=0;i<10;i++){
                                    System.out.println();
                                }
                                UtilMethods.waitForEnter(scanner);
                                return;
                            }

                            System.out.println();
                            double targetHours = InputValidator.getValidSleepDuration(Color.SOFT_LAVENDER+"\nEnter new target sleep time (in hr): "+Color.RESET,scanner);

                            goalManager.editGoal(id,date,targetHours);

                            for(int i=0;i<5;i++){
                                System.out.println();
                            }
                            System.out.println(Color.GREEN+"Goal with ID " + id + " edited successfully!"+Color.RESET);

                            for(int i=0;i<6;i++){
                                System.out.println();
                            }
                            UtilMethods.waitForEnter(scanner);
                            return;

                        } catch (NumberFormatException e) {
                            System.out.println(Color.RED+"\nInvalid Log ID! Please enter a valid ID."+Color.RESET);
                        }
                }
        }
    }

    public void deleteGoal(){

        if (goalManager.getGoals().isEmpty()){
            System.out.println(Color.RED+"\n                                                                     No goals available for now."+Color.RESET);
            for(int i=0;i<6;i++){
                System.out.println();
            }
            UtilMethods.waitForEnter(scanner);
        }
        else {
            viewGoals();
            System.out.println();
            while (true){

                try {
                    System.out.println();
                    System.out.print(Color.SOFT_LAVENDER+"\nEnter the ID of the goal you want to delete: "+Color.RESET);

                    String input=scanner.nextLine().trim();
                    int id=Integer.parseInt(input);

                    if (!goalManager.goalIdExist(id)){
                        System.out.println(Color.RED+"\nInvalid goal ID! Please enter a valid ID."+Color.RESET);
                        continue;
                    }


                    goalManager.deleteGoal(id);

                    for(int i=0;i<5;i++){
                        System.out.println();
                    }

                    System.out.println(Color.GREEN+"\nGoal with ID " + id + " deleted successfully!"+Color.RESET);

                    for(int i=0;i<10;i++){
                        System.out.println();
                    }
                    UtilMethods.waitForEnter(scanner);
                    return;

                } catch (NumberFormatException e) {
                    System.out.println(Color.RED+"\nInvalid Log ID! Please enter a valid ID."+Color.RESET);
                }
            }
        }
    }

    public void viewGoals() {
        List<String[]> goalsData = goalManager.viewGoals();

        System.out.println();
        System.out.println("\n                                                                          ══════════════════════════════");
        System.out.println("                                                                                 All Sleep Goals");
        System.out.println("                                                                          ══════════════════════════════");

        for(int i=0;i<5;i++){
            System.out.println();
        }

        if (goalsData.isEmpty()) {
            System.out.println(Color.RED+"\n                                                                            No goals available for now."+Color.RESET);
            return;
        }


        System.out.println("                                                                 +------+---------------+-------------------+");
        System.out.printf("                                                                 | %s%-4s%s | %s%-12s%s  | %s%-16s%s  |\n",
                Color.SOFT_LAVENDER, "ID", Color.RESET,
                Color.SOFT_LAVENDER, "Date", Color.RESET,
                Color.SOFT_LAVENDER, "Target Hours", Color.RESET);
        System.out.println("                                                                 +------+---------------+-------------------+");

        for (String[] goal : goalsData) {
            System.out.printf("                                                                 | %-4s | %-12s  | %-16s  |\n", goal[0], goal[1], goal[2]);
        }


        System.out.println("                                                                 +------+---------------+-------------------+");
    }

    public void getProgressReport(){

        this.reportData=progressTracker.generateReport();

        System.out.println();
        System.out.println("\n                                                                          ══════════════════════════════");
        System.out.println("                                                                                Goal Status Overview");
        System.out.println("                                                                          ══════════════════════════════");


        for(int i=0;i<5;i++){
            System.out.println();
        }

        if (goalManager.getGoals().isEmpty()){
            System.out.println(Color.RED+"\n                                                                No data available for now. Set a goal to track progress."+Color.RESET);
        }
        else {

            String separator = "                                                              +------------+--------------+-------------+--------------+";
            System.out.println(separator);
            System.out.printf("                                                              | %s%-10s%s | %s%-12s%s | %s%-11s%s | %s%-12s%s |\n",
                    Color.SOFT_LAVENDER, "Date", Color.RESET,
                    Color.SOFT_LAVENDER, "Target Hours", Color.RESET,
                    Color.SOFT_LAVENDER, "Hours Slept", Color.RESET,
                    Color.SOFT_LAVENDER, "Status", Color.RESET);
            System.out.println(separator);


            for (Map<String, String> entry : reportData) {
                System.out.printf("                                                              | %-10s | %-12s | %-11s | %-12s |\n",
                        entry.get("Date"),
                        entry.get("Target Hours"),
                        entry.get("Hours Slept"),
                        entry.get("Status"));
            }

            System.out.println(separator);

        }

        for(int i=0;i<10;i++){
            System.out.println();
        }

        UtilMethods.waitForEnter(scanner);
    }


    public void manageGoals () {

        while (true) {

            UtilMethods.clearTerminal();

            GoalMenu();

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                scanner.nextLine();

                switch (choice) {
                    case 1:
                        UtilMethods.clearTerminal();
                        addGoal();
                        break;
                    case 2:
                        UtilMethods.clearTerminal();
                        editGoal();
                        break;
                    case 3:
                        UtilMethods.clearTerminal();
                        deleteGoal();
                        break;
                    case 4:
                        UtilMethods.clearTerminal();
                        viewGoals();
                        for(int i=0;i<10;i++){
                            System.out.println();
                        }
                        UtilMethods.waitForEnter(scanner);
                        break;
                    case 5:
                        UtilMethods.clearTerminal();
                        getProgressReport();
                        break;
                    case 6:
                        UtilMethods.clearTerminal();
                        return;
                    default:
                        System.out.println(Color.RED+"\nInvalid option. Please Try again."+Color.RESET);
                        System.out.println();

                }
            } else {
                System.out.println(Color.RED+"\nInvalid option. Please enter a valid number."+Color.RESET);
                System.out.println();
                scanner.nextLine();
            }

        }
    }

    public void GoalMenu () {
        System.out.println();

        System.out.println("\n                                                                   ╔════════════════════════════════════╗");
        System.out.println("                                                                   ║            Manage Goals            ║");
        System.out.println("                                                                   ╠════════════════════════════════════╣");
        System.out.println("                                                                   ║ 1. Set a goal                      ║");
        System.out.println("                                                                   ║ 2. Edit goals                      ║");
        System.out.println("                                                                   ║ 3. Delete goals                    ║");
        System.out.println("                                                                   ║ 4. View All goals                  ║");
        System.out.println("                                                                   ║ 5. Track Progress                  ║");
        System.out.println("                                                                   ║ 6. Go Back                         ║");
        System.out.println("                                                                   ╚════════════════════════════════════╝");
        System.out.print(Color.CYAN+"\nEnter your choice: "+Color.RESET);


    }
}
