import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class SleepLogUI {
    private ISleepLogManager logManager;
    private ISleepLogCommentGenerator sleepLogCommentGenerator;
    Scanner scanner;

    public SleepLogUI(ISleepLogManager logManager,ISleepLogCommentGenerator sleepLogCommentGenerator,Scanner scanner){
        this.logManager=logManager;
        this.sleepLogCommentGenerator=sleepLogCommentGenerator;
        this.scanner=scanner;
    }

    public void addLog(){

        System.out.println();
        System.out.println("\n                                                                          ══════════════════════════════");
        System.out.println("                                                                                   Add a Sleep Log");
        System.out.println("                                                                          ══════════════════════════════");


        for(int i=0;i<5;i++){
            System.out.println();
        }


        LocalDate date = InputValidator.getValidDate(Color.SOFT_LAVENDER+"\nEnter date (yyyy-MM-dd):"+Color.RESET,scanner);
        LocalTime startTime = InputValidator.getValidTime(Color.SOFT_LAVENDER+"\nEnter the time you went to bed (e.g., 10:30 PM): "+Color.RESET,scanner);
        LocalTime endTime = InputValidator.getValidTime(Color.SOFT_LAVENDER+"\nEnter the time you got up from bed (e.g., 6:30 AM): "+Color.RESET,scanner);
        double actualDuration= InputValidator.getValidSleepDuration(Color.SOFT_LAVENDER+"\nEnter the duration you actually slept (in hour): "+Color.RESET,scanner);

        logManager.addSleepLog(date,startTime,endTime,actualDuration);

        for(int i=0;i<5;i++){
            System.out.println();
        }
        System.out.println(Color.GREEN+"\nSleep Log added successfully!"+Color.RESET);

        for(int i=0;i<5;i++){
            System.out.println();
        }
        System.out.println(Color.PURPLE+"Comment: "+Color.RESET+sleepLogCommentGenerator.generateLogComment(date));

        for(int i=0;i<10;i++){
            System.out.println();
        }

        UtilMethods.waitForEnter(scanner);
    }

    public void editLog(){

        if(logManager.getSleepLogs().isEmpty()){
            System.out.println(Color.RED+"\n                                                                       No sleep logs available for now."+Color.RESET);
            for(int i=0;i<6;i++){
                System.out.println();
            }
            UtilMethods.waitForEnter(scanner);
        }
        else {
            viewLogs();

            while (true) {

                try {


                    System.out.print(Color.SOFT_LAVENDER+"\nEnter the ID of the log you want to edit: "+Color.RESET);

                    String input=scanner.nextLine().trim();
                    int id=Integer.parseInt(input);

                    if (!logManager.logIdExists(id)){
                        System.out.println(Color.RED+"\nInvalid Log ID! Please enter a valid ID."+Color.RESET);
                        continue;
                    }

                    LocalDate date = InputValidator.getValidDate(Color.SOFT_LAVENDER+"\nEnter date (yyyy-MM-dd):"+Color.RESET,scanner);
                    LocalTime startTime = InputValidator.getValidTime(Color.SOFT_LAVENDER+"\nEnter the time you went to bed (e.g., 10:30 PM): "+Color.RESET,scanner);
                    LocalTime endTime = InputValidator.getValidTime(Color.SOFT_LAVENDER+"\nEnter the time you got up from bed (e.g., 6:30 AM): "+Color.RESET,scanner);
                    double actualDuration = InputValidator.getValidSleepDuration(Color.SOFT_LAVENDER+"\nEnter the duration you actually slept (in hour): "+Color.RESET,scanner);

                    logManager.editLogs(id, date, startTime, endTime, actualDuration);

                    for(int i=0;i<5;i++){
                        System.out.println();
                    }
                    System.out.println(Color.GREEN+"\nLog with ID " + id + " edited successfully!"+Color.RESET);

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

    public void deleteLog(){


        if(logManager.getSleepLogs().isEmpty()){
            System.out.println(Color.RED+"\n                                                                       No sleep logs available for now."+Color.RESET);
            for(int i=0;i<6;i++){
                System.out.println();
            }
            UtilMethods.waitForEnter(scanner);
        }
        else {
            viewLogs();

            while (true) {

                try {

                    System.out.print(Color.SOFT_LAVENDER+"\nEnter the ID of the log you want to delete: "+Color.RESET);

                    String input=scanner.nextLine().trim();
                    int id=Integer.parseInt(input);

                    if (!logManager.logIdExists(id)){
                        System.out.println(Color.RED+"\nInvalid Log ID! Please enter a valid ID."+Color.RESET);
                        continue;
                    }

                    logManager.deleteLog(id);
                    for(int i=0;i<5;i++){
                        System.out.println();
                    }
                    System.out.println(Color.GREEN+"\nLog with ID " + id + " deleted successfully!"+Color.RESET);

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

    public void viewLogs() {
        try {

            List<String[]> logsData=logManager.viewLogs();


            System.out.println();
            System.out.println("\n                                                                          ══════════════════════════════");
            System.out.println("                                                                                   All Sleep Logs");
            System.out.println("                                                                          ══════════════════════════════");

            System.out.println();


            if (logManager.getSleepLogs().isEmpty()) {
                System.out.println(Color.RED+"\n                                                                          No sleep logs available for now."+Color.RESET);
                return;
            }



            System.out.println("                                                     +-----+------------+------------+------------+----------------------+");
            System.out.printf("                                                     | %-3s | %-10s | %-10s | %-10s | %-20s|\n",
                    "ID", "Date", "Start Time", "End Time", "Actual Sleep Duration");

            System.out.println("                                                     +-----+------------+------------+------------+----------------------+");
            for (String[] log : logsData) {
                System.out.printf("                                                     | %-3s | %-10s | %-10s | %-10s | %-20s |\n",
                        log[0], log[1], log[2], log[3], log[4]);
            }


            System.out.println("                                                     +-----+------------+------------+------------+----------------------+");

        } catch (Exception e) {
            System.out.println("\nAn unknown error occurred while viewing logs.");
        }
    }




    public void manageSleepLogs () {

        while (true) {

            UtilMethods.clearTerminal();

            SleepLogMenu();

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                scanner.nextLine();

                switch (choice) {
                    case 1:
                        UtilMethods.clearTerminal();
                        addLog();
                        break;
                    case 2:
                        UtilMethods.clearTerminal();
                        editLog();
                        break;
                    case 3:
                        UtilMethods.clearTerminal();
                        deleteLog();
                        break;
                    case 4:
                        UtilMethods.clearTerminal();
                        viewLogs();
                        for(int i=0;i<10;i++){
                            System.out.println();
                        }
                        UtilMethods.waitForEnter(scanner);
                        break;
                    case 5:
                        UtilMethods.clearTerminal();
                        return;
                    default:
                        System.out.println(Color.RED+"Invalid option. Please Try again."+Color.RESET);
                        System.out.println();

                }
            } else {
                System.out.println(Color.RED+"Invalid option. Please enter a valid number."+Color.RESET);
                System.out.println();
                scanner.nextLine();
            }

        }
    }

    public void SleepLogMenu () {
        System.out.println();

        System.out.println("\n                                                                   ╔════════════════════════════════════╗");
        System.out.println("                                                                   ║             Manage Logs            ║");
        System.out.println("                                                                   ╠════════════════════════════════════╣");
        System.out.println("                                                                   ║ 1. Add a Log                       ║");
        System.out.println("                                                                   ║ 2. Edit Log                        ║");
        System.out.println("                                                                   ║ 3. Delete Log                      ║");
        System.out.println("                                                                   ║ 4. View All Logs                   ║");
        System.out.println("                                                                   ║ 5. Go Back                         ║");
        System.out.println("                                                                   ╚════════════════════════════════════╝");
        System.out.print(Color.CYAN+"\nEnter your choice: "+Color.RESET);

    }


}
