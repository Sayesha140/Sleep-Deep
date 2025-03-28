import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReportUI {

    private IReportGenerator reportGenerator;
    private List<Map<String, String>> reportData;
    Scanner scanner;

    ReportUI(IReportGenerator reportGenerator,Scanner scanner) {
        this.reportGenerator = reportGenerator;
        this.scanner=scanner;
    }


    public void displayLogReport() {

        reportData=reportGenerator.generateReport();


        System.out.println();

        System.out.println();
        System.out.println("\n                                                                          ══════════════════════════════");
        System.out.println("                                                                                 Analyzed Overview");
        System.out.println("                                                                          ══════════════════════════════");


        for(int i=0;i<5;i++){
            System.out.println();
        }


        if (reportData.isEmpty()) {
            System.out.println(Color.RED+"                                                                             No sleep logs available."+Color.RESET);
            for(int i=0;i<6;i++){
                System.out.println();
            }
            UtilMethods.waitForEnter(scanner);
            UtilMethods.clearTerminal();
            return;
        }

        String separator = "+------------+--------------------+------------------------+-----------------+----------------------+";
        System.out.println(separator);
        System.out.printf("| %-10s | %-18s | %-22s | %-15s | %-20s |\n",
                "Date", "Total Time in Bed", "Total Sleep Duration", "Sleep Debt", "Comment");
        System.out.println(separator);


        for (Map<String, String> entry : reportData) {
            System.out.printf("| %-10s | %-18s | %-22s | %-15s | %-20s |\n",
                    entry.get("Date"),
                    entry.get("Total Time in Bed"),
                    entry.get("Total Sleep Duration"),
                    entry.get("Sleep Debt"),
                    entry.get("Comment"));
        }

        System.out.println(separator);
        System.out.println();

        for(int i=0;i<10;i++){
            System.out.println();
        }

        UtilMethods.waitForEnter(scanner);
        UtilMethods.clearTerminal();
    }


}
