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

        System.out.println("Please Provide Your Details To Get Started..");

        System.out.println();
        double age=UserInputValidator.getValidAge(scanner);

        System.out.println();
        Gender gender=UserInputValidator.getValidGender(scanner,"Enter Your Gender (MALE/FEMALE): ");

        System.out.println();
        ActivityLevel activityLevel=UserInputValidator.getValidActivityLevel(scanner,"Enter Your Activity Level (Low/Medium/High): ");

        user=new User(age,gender,activityLevel);
        sleepTimeRecommendationStrategy=SleepRecommendationFactory.getSleepTimeRecommendation(user);

        userFileManager.saveUserData(user);

        System.out.println();
        System.out.println("Your Profile is Set!");

        System.out.println();
        System.out.println("Recommendation: Based on your profile, you should aim to get " +sleepTimeRecommendationStrategy.getMinSleep()+"-"+ sleepTimeRecommendationStrategy.getAdjustedMaxSleep(user)+ " hours of sleep daily.");

        UtilMethods.waitForEnter(scanner);

    }

    public void updateProfile(){

        System.out.println("══════════════════════════════");
        System.out.println("        Current Profile");
        System.out.println("══════════════════════════════");
        System.out.println();
        displayUserProfile();
        System.out.println();
        System.out.print("Press Enter to continue updating your details.");
        scanner.nextLine();
        System.out.println();

        double updatedAge=UserInputValidator.getValidAge(scanner);
        System.out.println();
        Gender updatedGender=UserInputValidator.getValidGender(scanner,"Enter updated Gender (MALE/FEMALE): ");
        System.out.println();
        ActivityLevel updatedActivityLevel=UserInputValidator.getValidActivityLevel(scanner,"Enter updated Activity Level (Low/Medium/High): ");



        user=new User(updatedAge,updatedGender,updatedActivityLevel);
        sleepTimeRecommendationStrategy=SleepRecommendationFactory.getSleepTimeRecommendation(user);

        userFileManager.saveUserData(user);
        System.out.println();

        System.out.println("Your Profile is Updated!");


        System.out.println();
        System.out.println("══════════════════════════════");
        System.out.println("        Updated Profile");
        System.out.println("══════════════════════════════");
        System.out.println();
        displayUserProfile();

        UtilMethods.waitForEnter(scanner);


    }
    public void displayUserProfile(){

        String formattedAge=getFormattedAge();
        System.out.println("Age: " +formattedAge);
        System.out.println("Gender: " +StringFormatter.capitalizeFirstLetter(user.getGender().toString()));
        System.out.println("Activity Level: " +StringFormatter.capitalizeFirstLetter(user.getActivityLevel().toString()));
        System.out.println("Recommended Sleep Duration: " +sleepTimeRecommendationStrategy.getMinSleep()+ "-" +sleepTimeRecommendationStrategy.getAdjustedMaxSleep(user)+ " hours");
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
                        displayUserProfile();
                        UtilMethods.waitForEnter(scanner);
                        break;
                    case 2:
                        updateProfile();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid option. Please Try again.");
                        System.out.println();
                }
            } else {
                System.out.println("Invalid option. Please enter a valid number.");
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
        System.out.println("                                                                 ║          1. View Profile           ║");
        System.out.println("                                                                 ║          2. Update Profile         ║");
        System.out.println("                                                                 ║          3. Back to Main Menu      ║");
        System.out.println("                                                                 ╚════════════════════════════════════╝");
        System.out.println();
        System.out.print("Enter your choice: ");

    }


}

