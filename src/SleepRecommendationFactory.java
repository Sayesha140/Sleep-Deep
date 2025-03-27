public class SleepRecommendationFactory {
    public static SleepTimeRecommendationStrategy getSleepTimeRecommendation(User user){

        if (user==null){
            return null;
        }

        if (user.getAge()<1){
            return new InfantSleepTimeRecommendation();
        }
        else if (user.getAge()>=1 && user.getAge()<=2) {
            return new ToddlerSleepTimeRecommendation();
        }
        else if (user.getAge()>=3 && user.getAge()<=5) {
            return new PreSchoolSleepTimeRecommendation();
        }
        else if(user.getAge()>=6 && user.getAge()<=12){
            return new SchoolAgeSleepTimeRecommendation();
        }
        else if(user.getAge()>=13 && user.getAge()<=18){
            return new TeenSleepTimeRecommendation();
        }
        else {
            return new AdultSleepTimeRecommendation();
        }

    }
}
