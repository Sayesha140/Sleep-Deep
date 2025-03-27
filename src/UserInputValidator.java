import java.util.Scanner;

public class UserInputValidator {
    public static double getValidAge(Scanner scanner) {
        int choice;


        while (true) {
            System.out.println("\nEnter age in:");
            System.out.println("1. Years");
            System.out.println("2. Months");
            System.out.print("\nEnter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("\nInvalid choice. Please enter 1 for years or 2 for months.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter 1 or 2.");
            }
        }


        while (true) {
            try {
                if (choice == 1) {
                    System.out.print("\nEnter age in years: ");
                    double age = Double.parseDouble(scanner.nextLine().trim());
                    if (age > 0 && age < 100) {
                        return age;
                    } else {
                        System.out.println("\nPlease enter a valid age (>0-100). Try again.");
                    }
                } else {
                    System.out.print("\nEnter age in months: ");
                    int months = Integer.parseInt(scanner.nextLine().trim());
                    if (months > 0 ) {
                        return months / 12.0;
                    } else {
                        System.out.println("\nPlease enter a valid number of months. Try again.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a valid number.");
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
                System.out.println("Invalid input. Please enter either MALE or FEMALE.");
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
                System.out.println("Invalid input. Please enter one of the following: Low,Medium,High.");
            }
        }
    }
}