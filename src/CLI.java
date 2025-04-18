import java.io.File;
import java.util.Scanner;

public class CLI {
    private static final Scanner scanner=new Scanner(System.in);
    public void start(){

        UtilMethods.clearTerminal();
        boolean running=true;


        while (running){


            UserFileManager userFileManager=UserFileFactory.getUserFile("User_data.csv");
            User user=userFileManager.loadUserData();

            SleepTimeRecommendationStrategy recommendationStrategy=SleepRecommendationFactory.getSleepTimeRecommendation(user);

            UserUI userUI=new UserUI(user,recommendationStrategy,userFileManager,scanner);



            System.out.println();


            if (!userExists()){
                System.out.println(Color.CYAN+"                                                                   ═════════════════════════════════════════");
                System.out.println("                                                                                  \u001B[1m SLEEP DEEP\u001B[0m");
                System.out.println(Color.CYAN+"                                                                   ═════════════════════════════════════════"+Color.RESET);

                userUI.setProfile();
            }



            User user2=userFileManager.loadUserData();

            SleepTimeRecommendationStrategy recommendationStrategy2=SleepRecommendationFactory.getSleepTimeRecommendation(user2);


            ISleepLogStorageManager logStorageManager=SleepLogFileFactory.getLogFile("sleepLogs.csv");
            IGoalStorageManager goalStorageManager=GoalFileFactory.getGoalFile("goals.csv");

            IGoalManager goalManager=new GoalManager(goalStorageManager);
            ISleepLogManager logManager=new SleepLogManager(logStorageManager);
            ISleepLogAnalyzer analyzer=new LogAnalyzer(logManager);

            IReportGenerator progressTracker=new ProgressTracker(goalManager,analyzer);
            IReportGenerator logReportGenerator=new LogReportGenerator(logManager, analyzer,recommendationStrategy2,user2);
            ITipGenerator tipGenerator=new QuickTipGenerator();

            BasicLogCommentGenerator sleepLogCommentGenerator=new BasicLogCommentGenerator(analyzer,recommendationStrategy2,user2);

            ReportUI reportUI=new ReportUI(logReportGenerator,scanner);
            SleepLogUI sleepLogUI=new SleepLogUI(logManager,sleepLogCommentGenerator,scanner);
            GoalUI goalUI=new GoalUI(goalManager,progressTracker,scanner);
            SleepTipsUI sleepTipsUI=new SleepTipsUI(scanner);






            System.out.println(Color.CYAN+"                                                                   ═════════════════════════════════════════");
            System.out.println("                                                                                  \u001B[1m SLEEP DEEP\u001B[0m");
            System.out.println(Color.CYAN+"                                                                   ═════════════════════════════════════════"+Color.RESET);


            System.out.println();

            System.out.println();
            System.out.println(Color.YELLOW+"\nQuick Tip"+Color.RESET);
            System.out.println();
            System.out.println(Color.SOFT_LAVENDER+tipGenerator.generateTips()+Color.RESET);

            System.out.println();

            displayMenu();

            if (scanner.hasNextInt()) {

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        userUI.manageProfile();
                        break;
                    case 2:
                        sleepLogUI.manageSleepLogs();
                        break;
                    case 3:
                        goalUI.manageGoals();
                        break;
                    case 4:
                        UtilMethods.clearTerminal();
                        reportUI.displayLogReport();
                        break;
                    case 5:
                        UtilMethods.clearTerminal();
                        sleepTipsUI.manageTips();
                        break;
                    case 6:
                        System.out.println();
                        for(int i=0;i<4;i++){
                            System.out.println();
                        }
                        UtilMethods.clearTerminal();
                        for(int i=0;i<7;i++){
                            System.out.println();
                        }
                        System.out.println(Color.SOFT_LAVENDER+ "                                                          Thank you for using Sleep Deep! Sleep well, Live better!"+Color.RESET);
                        for(int i=0;i<7;i++){
                            System.out.println();
                        }
                        running = false;
                        break;
                    default:
                        System.out.println();
                        System.out.println(Color.RED+"\nInvalid option. Try again."+Color.RESET);
                        System.out.println();

                }
            }
            else {
                System.out.println();
                System.out.println(Color.RED+"\nInvalid option. Please enter a valid number."+Color.RESET);
                scanner.nextLine();
            }

        }
    }

    private void displayMenu(){

        System.out.println();

        System.out.println("\n                                                                   ╔════════════════════════════════════╗");
        System.out.println("                                                                   ║             Main Menu              ║");
        System.out.println("                                                                   ╠════════════════════════════════════╣");
        System.out.println("                                                                   ║ 1. Your Profile                    ║");
        System.out.println("                                                                   ║ 2. Manage Sleep Logs               ║");
        System.out.println("                                                                   ║ 3. Manage Goals                    ║");
        System.out.println("                                                                   ║ 4. Analyzed Overview Of Logs       ║");
        System.out.println("                                                                   ║ 5. Get Personalized Sleep Tips     ║");
        System.out.println("                                                                   ║ 6. Exit                            ║");
        System.out.println("                                                                   ╚════════════════════════════════════╝");
        System.out.print(Color.CYAN+"\nEnter Your Choice: "+Color.RESET);

    }

    private boolean userExists(){
        File profileFile = new File("User_data.csv");
        return profileFile.exists() && profileFile.length() > 0;
    }

}
