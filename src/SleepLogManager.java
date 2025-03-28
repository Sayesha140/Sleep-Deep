import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class SleepLogManager implements ISleepLogManager {

    private final List<SleepLog>sleepLogs;
    private final ISleepLogStorageManager storage;

    public SleepLogManager(ISleepLogStorageManager storage){
        this.storage=storage;
        this.sleepLogs=storage.loadLogs();
    }

    public List<SleepLog> getSleepLogs() {
        return Collections.unmodifiableList(sleepLogs);
    }

    @Override
    public void addSleepLog(LocalDate date, LocalTime startTime, LocalTime endTime, double sleepDuration){
        int id=sleepLogs.size()+1;
        SleepLog log=new SleepLog(id,date,startTime,endTime,sleepDuration);
        sleepLogs.add(log);
        storage.saveLogs(sleepLogs);

    }

    @Override
    public void editLogs(int id,LocalDate date, LocalTime startTime, LocalTime endTime, double sleepDuration) {
            SleepLog editedLog=new SleepLog(id,date,startTime,endTime,sleepDuration);
            sleepLogs.set(id-1,editedLog);
            storage.saveLogs(sleepLogs);
    }

    @Override
    public void deleteLog(int id){
             sleepLogs.remove(id-1);
             reorganizeId();
             storage.saveLogs(sleepLogs);
    }

    @Override
    public List<String[]> viewLogs(){
        sleepLogs.sort(Comparator.comparing(SleepLog::getDate));
        reorganizeId();

        DateTimeFormatter twelveHourFormatter = DateTimeFormatter.ofPattern("h:mm a");

        List<String[]> logDataList = new ArrayList<>();

        for (SleepLog log : sleepLogs) {
            logDataList.add(new String[]{
                    String.valueOf(log.getId()),
                    log.getDate().toString(),
                    log.getStartTime().format(twelveHourFormatter),
                    log.getEndTime().format(twelveHourFormatter),
                    String.format("%.2f hours", log.getSleepDuration())
            });
        }

        return logDataList;
    }

    private void reorganizeId(){
        for (int i=0;i<sleepLogs.size();i++){
            sleepLogs.get(i).setId(i+1);
        }
    }


    @Override
    public Map<LocalDate, List<SleepLog>> groupLogsByDate() {
        return sleepLogs.stream()
                .collect(Collectors.groupingBy(SleepLog::getDate, TreeMap::new, Collectors.toList()));
    }
    @Override
    public List<SleepLog> getLogsForDate(LocalDate date){
        List<SleepLog> logsForDate=new ArrayList<>();

        for (SleepLog log:sleepLogs){
            if (log.getDate().equals(date)){
                logsForDate.add(log);
            }
        }

        return logsForDate;
    }

    @Override
    public double calculateTotalSleepDurationByDate(LocalDate date){

        double totalSleep=0;
        List<SleepLog> logs=getLogsForDate(date);

        for (SleepLog log:logs){
            totalSleep+=log.getSleepDuration();
        }
        return totalSleep;
    }

    @Override
    public double calculateTotalTimeInBedByDate(LocalDate date){

        double totalTimeInBed = 0;
        List<SleepLog> logs=getLogsForDate(date);

        for (SleepLog log : logs) {
            Duration duration = Duration.between(log.getStartTime(), log.getEndTime());

            if (duration.isNegative()) {
                duration = duration.plusHours(24);
            }

            totalTimeInBed += duration.toMinutes() / 60.0;
        }

        return totalTimeInBed;
    }

    @Override
    public boolean logIdExists(int id) {
        return getSleepLogs().stream().anyMatch(log -> log.getId() == id);
    }

}
