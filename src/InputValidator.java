import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidator {

    static Scanner scanner;
    public InputValidator(Scanner scanner){
        this.scanner=scanner;

    }

    public static LocalDate getValidDate(String prompt,Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in the format yyyy-MM-dd.");
            }
        }
    }

    public static LocalTime getValidTime(String prompt,Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalTime.parse(input, DateTimeFormatter.ofPattern("h:mm a"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please enter in the format h:mm AM/PM.");
            }
        }
    }

    public static double getValidSleepDuration(String prompt,Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            try {
                double duration = Double.parseDouble(scanner.nextLine().trim());
                if (duration > 0) {
                    return duration;
                } else {
                    System.out.println("Please enter a valid duration (in hour).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid duration.");
            }
        }
    }

}