import java.util.Scanner;

public class UserUI {

    private User user;
    private SleepTimeRecommendationStrategy sleepTimeRecommendationStrategy;
    private UserFileManager userFileManager;
    Scanner scanner;

    public UserUI(User user,SleepTimeRecommendationStrategy sleepTimeRecommendationStrategy,UserFileManager userFileManager, Scanner scanner){
        this.user=user;
        this.sleepTimeRecommendationStrategy=sleepTimeRecommendationStrategy;
        this.userFileManager=userFileManager;
        this.scanner=scanner;
    }

    public void setProfile(){

        System.out.println(Color.PURPLE+"Please Provide Your Details To Get Started.."+Color.RESET);

        System.out.println();
        double age=UserInputValidator.getValidAge(scanner);

        System.out.println();
        Gender gender=UserInputValidator.getValidGender(scanner,Color.SOFT_LAVENDER+"\nEnter Your Gender (MALE/FEMALE): "+Color.RESET);

        System.out.println();
        ActivityLevel activityLevel=UserInputValidator.getValidActivityLevel(scanner,Color.SOFT_LAVENDER+"\nEnter Your Activity Level (Low/Medium/High): "+Color.RESET);

        user=new User(age,gender,activityLevel);
        sleepTimeRecommendationStrategy=SleepRecommendationFactory.getSleepTimeRecommendation(user);

        userFileManager.saveUserData(user);

        for(int i=0;i<5;i++){
            System.out.println();
        }
        System.out.println(Color.CYAN+"Your Profile is Set!"+Color.RESET);

        for(int i=0;i<5;i++){
            System.out.println();
        }
        System.out.println(Color.YELLOW+"\nRecommendation: "+Color.RESET+"Based on your profile, you should aim to get " +sleepTimeRecommendationStrategy.getMinSleep()+"-"+ sleepTimeRecommendationStrategy.getAdjustedMaxSleep(user)+ " hours of sleep daily.");

        for(int i=0;i<6;i++){
            System.out.println();
        }

        UtilMethods.waitForEnter(scanner);
        UtilMethods.clearTerminal();

    }

    public void updateProfile(){

        UtilMethods.clearTerminal();

        displayUserProfile();
        System.out.println();
        System.out.print(Color.CYAN+"Press Enter to continue updating your details..."+Color.RESET);
        scanner.nextLine();
        System.out.println();

        double updatedAge=UserInputValidator.getValidAge(scanner);
        System.out.println();
        Gender updatedGender=UserInputValidator.getValidGender(scanner,Color.SOFT_LAVENDER+"\nEnter updated Gender (MALE/FEMALE): "+Color.RESET);
        System.out.println();
        ActivityLevel updatedActivityLevel=UserInputValidator.getValidActivityLevel(scanner,Color.SOFT_LAVENDER+"\nEnter updated Activity Level (Low/Medium/High): "+Color.RESET);



        user=new User(updatedAge,updatedGender,updatedActivityLevel);
        sleepTimeRecommendationStrategy=SleepRecommendationFactory.getSleepTimeRecommendation(user);

        userFileManager.saveUserData(user);

        for(int i=0;i<3;i++){
            System.out.println();
        }


        System.out.println(Color.GREEN+"\nYour Profile is Updated!"+Color.RESET);

        for(int i=0;i<3;i++){
            System.out.println();
        }

        System.out.println(Color.YELLOW+"\nRecommendation: "+Color.RESET+"Based on your profile, you should aim to get " +sleepTimeRecommendationStrategy.getMinSleep()+"-"+ sleepTimeRecommendationStrategy.getAdjustedMaxSleep(user)+ " hours of sleep daily.");


        for(int i=0;i<2;i++){
            System.out.println();
        }
        UtilMethods.waitForEnter(scanner);


    }

    public void displayUserProfile() {
        String formattedAge = getFormattedAge();
        String gender = StringFormatter.capitalizeFirstLetter(user.getGender().toString());
        String activityLevel = StringFormatter.capitalizeFirstLetter(user.getActivityLevel().toString());
        String sleepDuration = sleepTimeRecommendationStrategy.getMinSleep() + "-" +
                sleepTimeRecommendationStrategy.getAdjustedMaxSleep(user) + " hours";

        System.out.println();
        System.out.println("\n                                                               ╔═════════════════════════════════════════════════════════╗");
        System.out.println("                                                               ║                     Your Profile                        ║");
        System.out.println("                                                               ╠═════════════════════════════════════════════════════════╣");
        System.out.printf("                                                               ║ %-35s:%-20s║%n", "Age", formattedAge);
        System.out.printf("                                                               ║ %-35s:%-20s║%n", "Gender", gender);
        System.out.printf("                                                               ║ %-35s:%-20s║%n", "Activity Level", activityLevel);
        System.out.printf("                                                               ║ %-35s:%-20s║%n", "Recommended Sleep Duration", sleepDuration);
        System.out.println("                                                               ╚═════════════════════════════════════════════════════════╝");
        System.out.println();
    }



    private String getFormattedAge(){

        double age = user.getAge();

        return  (age < 1)
                ? (int) (age * 12) + " months"
                : String.format("%.1f years", age);
    }


    public boolean isProfileSet(){
        return userFileManager.userExists();
    }


    public void manageProfile(){

        while (true) {
            UtilMethods.clearTerminal();
            userMenu();

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                scanner.nextLine();

                switch (choice) {
                    case 1:
                        UtilMethods.clearTerminal();
                        displayUserProfile();
                        for(int i=0;i<10;i++){
                            System.out.println();
                        }
                        UtilMethods.waitForEnter(scanner);
                        break;
                    case 2:
                        updateProfile();
                        break;
                    case 3:
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
    public void userMenu(){
        System.out.println();
        System.out.println("\n                                                                 ╔════════════════════════════════════╗");
        System.out.println("                                                                 ║           Manage Profile           ║");
        System.out.println("                                                                 ╠════════════════════════════════════╣");
        System.out.println("                                                                 ║ 1. View Profile                    ║");
        System.out.println("                                                                 ║ 2. Update Profile                  ║");
        System.out.println("                                                                 ║ 3. Go Back                         ║");
        System.out.println("                                                                 ╚════════════════════════════════════╝");
        System.out.println();
        System.out.print(Color.CYAN+"Enter your choice: "+Color.RESET);

    }


}
