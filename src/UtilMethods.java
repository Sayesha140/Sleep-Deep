import java.io.IOException;
import java.util.Scanner;

public class UtilMethods {

    public static void waitForEnter(Scanner scanner){
        System.out.println();
        System.out.print(Color.BLUE+"Press Enter to continue..."+Color.RESET);
        scanner.nextLine();
    }

    public static void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("\nFailed to clear terminal.");
        }
    }
}
