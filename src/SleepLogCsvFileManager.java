import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SleepLogCsvFileManager implements ISleepLogStorageManager{

    private final String SLEEP_LOGS_FILE= "sleepLogs.csv";

    @Override
    public void saveLogs(List<SleepLog> sleepLogs) {

        try(BufferedWriter writer=new BufferedWriter(new FileWriter(SLEEP_LOGS_FILE))) {

            for (SleepLog log:sleepLogs){
                writer.write(formatLog(log));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving log");
        }

    }

    @Override
    public List<SleepLog> loadLogs() {

        File file=new File(SLEEP_LOGS_FILE);

        if (!file.exists()){
            return new ArrayList<>();
        }

        List<SleepLog> sleepLogs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SLEEP_LOGS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.isBlank()){
                    continue;
                }
                sleepLogs.add(parseLog(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing logs for now.");
        } catch (IOException e) {
            System.err.println("Error loading logs.");
        }
        return sleepLogs;
    }


    private String formatLog(SleepLog log) {
        //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return log.getId() + "," +
                log.getDate() + "," +
                log.getStartTime()/*.format(formatter)*/ + "," +
                log.getEndTime()/*.format(formatter)*/ + "," +
                log.getSleepDuration();
    }


    private SleepLog parseLog(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        LocalDate date = LocalDate.parse(parts[1]);
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        LocalTime startTime = LocalTime.parse(parts[2] /*, formatter*/);
        LocalTime endTime = LocalTime.parse(parts[3] /*, formatter*/);
        double duration = Double.parseDouble(parts[4]);

        return new SleepLog(id, date, startTime, endTime, duration);
    }

}