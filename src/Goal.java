import java.time.LocalDate;

public class Goal {

    private int id;
    private LocalDate date;
    private double targetHours;

    public Goal(int id,LocalDate date,double targetHours){
        this.id=id;
        this.date=date;
        this.targetHours=targetHours;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTargetHours() {
        return targetHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: "+id+
                " Date: "+date+
                " Targeted sleep duration: "+targetHours+ " hours";
    }
}
