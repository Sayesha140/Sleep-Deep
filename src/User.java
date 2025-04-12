public class User {

    private double age;
    private Gender gender;
    private ActivityLevel activityLevel;

    public User(double age,Gender gender,ActivityLevel activityLevel){
        this.age=age;
        this.gender=gender;
        this.activityLevel=activityLevel;

    }

    public double getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    @Override
    public String toString() {
        return age + "," + gender + "," + activityLevel;
    }
}