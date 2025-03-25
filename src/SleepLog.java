import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class SleepLog {
    private int id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private double sleepDuration; // in hours


    public SleepLog(int id, LocalDate date, LocalTime startTime, LocalTime endTime, double sleepDuration) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sleepDuration = sleepDuration;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public double getSleepDuration() {
        return sleepDuration;
    }


    public void setId(int id) {
        this.id = id;
    }

    public double calculateTotalTimeInBed(){
        Duration durationInBed= Duration.between(startTime,endTime);

        if (durationInBed.isNegative()){
            durationInBed=durationInBed.plusHours(24);
        }
        return durationInBed.toMinutes()/60.0;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Date: " + date +
                ", Start Time in Bed: " + startTime +
                ", End Time in Bed: " + endTime +
                ", Actual Sleep Duration: " + sleepDuration + " hours";
    }
}