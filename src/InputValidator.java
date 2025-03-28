import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidator {

    public static LocalDate getValidDate(String prompt,Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                System.out.println(Color.RED+"\nInvalid date format. Please enter in the format yyyy-MM-dd."+Color.RESET);
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
                System.out.println(Color.RED+"\nInvalid time format. Please enter in the format h:mm AM/PM."+Color.RESET);
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
                    System.out.println(Color.RED+"\nPlease enter a valid duration (in hour)."+Color.RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(Color.RED+"\nInvalid input. Please enter a valid duration."+Color.RESET);
            }
        }
    }

}