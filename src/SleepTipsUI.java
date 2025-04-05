import java.util.Scanner;

public class SleepTipsUI {

    private TipFactory tipFactory;
    private Scanner scanner;

    public SleepTipsUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void manageTips() {
        while (true) {
            UtilMethods.clearTerminal();
            tipsMenu();

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                scanner.nextLine();

                switch (choice) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        UtilMethods.clearTerminal();
                        provideTips(choice);
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

    private void tipsMenu(){
        System.out.println("\n                                                                   ╔════════════════════════════════════╗");
        System.out.println("                                                                   ║       Sleep Tips and Guidance      ║");
        System.out.println("                                                                   ╠════════════════════════════════════╣");
        System.out.println("                                                                   ║ 1. Insomnia                        ║");
        System.out.println("                                                                   ║ 2. Frequent waking during sleep    ║");
        System.out.println("                                                                   ║ 3. Trouble falling asleep          ║");
        System.out.println("                                                                   ║ 4. Daytime fatigue                 ║");
        System.out.println("                                                                   ║ 5. Irregular sleep schedule        ║");
        System.out.println("                                                                   ║ 6. Go Back                         ║");
        System.out.println("                                                                   ╚════════════════════════════════════╝");
        System.out.print(Color.CYAN + "\nEnter your choice: " + Color.RESET);
    }

    private void provideTips(int choice) {
        SleepProblem problem = SleepProblem.values()[choice - 1];
        ITipGenerator tipGenerator = TipFactory.getTipGenerator(problem);

        System.out.println();
        System.out.println("\n                                                                             ══════════════════════════════");
        System.out.println(Color.SOFT_LAVENDER+"                                                                                Personalized Sleep Tips " +Color.RESET);
        System.out.println("                                                                             ══════════════════════════════");
        for (int i = 0; i < 4; i++) {
            System.out.println();
        }
        System.out.println(tipGenerator.generateTips());

        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
        UtilMethods.waitForEnter(scanner);
    }
}

