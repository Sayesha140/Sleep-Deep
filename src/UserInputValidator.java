import java.util.Scanner;

public class UserInputValidator {
    public static double getValidAge(Scanner scanner) {
        int choice;


        while (true) {
            System.out.println(Color.CYAN+"\nEnter age in:"+Color.RESET);
            System.out.println("\n1. Years");
            System.out.println("2. Months");
            System.out.print(Color.CYAN+"\nEnter your choice: "+Color.RESET);

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println(Color.RED+"\nInvalid choice. Please enter 1 for years or 2 for months."+Color.RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(Color.RED+"\nInvalid input. Please enter 1 or 2."+Color.RESET);
            }
        }


        while (true) {
            try {
                if (choice == 1) {
                    System.out.print(Color.SOFT_LAVENDER+"\nEnter age in years: "+Color.RESET);
                    double age = Double.parseDouble(scanner.nextLine().trim());
                    if (age > 0 && age < 100) {
                        return age;
                    } else {
                        System.out.println(Color.RED+"\nPlease enter a valid age (>0-100). Try again."+Color.RESET);
                    }
                } else {
                    System.out.print(Color.SOFT_LAVENDER+"\nEnter age in months: "+Color.RESET);
                    int months = Integer.parseInt(scanner.nextLine().trim());
                    if (months > 0 ) {
                        return months / 12.0;
                    } else {
                        System.out.println(Color.RED+"\nPlease enter a valid number of months. Try again."+Color.RESET);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(Color.RED+"\nInvalid input. Please enter a valid number."+Color.RESET);
            }
        }
    }

    public static Gender getValidGender(Scanner scanner,String prompt) {
        while (true) {
            System.out.print(prompt);
            String genderInput = scanner.nextLine().trim().toUpperCase();
            try {
                return Gender.valueOf(genderInput);
            } catch (IllegalArgumentException e) {
                System.out.println(Color.RED+"\nInvalid input. Please enter either MALE or FEMALE."+Color.RESET);
            }
        }
    }

    public static ActivityLevel getValidActivityLevel(Scanner scanner,String prompt) {
        while (true) {
            System.out.print(prompt);
            String activityInput = scanner.nextLine().trim().toUpperCase();
            try {
                return ActivityLevel.valueOf(activityInput);
            } catch (IllegalArgumentException e) {
                System.out.println(Color.RED+"\nInvalid input. Please enter one of the following: Low,Medium,High."+Color.RESET);
            }
        }
    }
}
