import java.time.LocalDate;

public class BasicLogCommentGenerator implements ISleepLogCommentGenerator {

    private final ISleepLogManager logManager;
    private final SleepTimeRecommendationStrategy recommendationStrategy;
    private User user;

    private static final double SEVERE_OVERSLEEP_THRESHOLD = 4.0;
    private static final double MODERATE_OVERSLEEP_THRESHOLD = 2.0;
    private static final double SLIGHT_OVERSLEEP_THRESHOLD = 1.0;

    public BasicLogCommentGenerator(ISleepLogManager logManager, SleepTimeRecommendationStrategy recommendationStrategy,User user) {
        this.logManager = logManager;
        this.recommendationStrategy = recommendationStrategy;
        this.user=user;
    }

    @Override
    public String generateLogComment(LocalDate date) {
        double totalTimeSlept = logManager.calculateTotalSleepDurationByDate(date);
        int minSleep = recommendationStrategy.getMinSleep();
        int maxSleep = recommendationStrategy.getAdjustedMaxSleep(user);
        double extraSleep = totalTimeSlept - maxSleep;

        return extraSleep > 0 ? getOverSleepingComment(extraSleep) :
                totalTimeSlept >= minSleep ? "Great job! You met your sleep goal." :
                        totalTimeSlept >= minSleep - 1 ? "Almost there! Try to sleep a little longer." :
                                "You need more rest. Consider adjusting your sleep schedule.";
    }

    private String getOverSleepingComment(double extraSleep) {
        return extraSleep >= SEVERE_OVERSLEEP_THRESHOLD ? "Are you okay? Oversleeping regularly may indicate underlying health issues."
                : extraSleep >= MODERATE_OVERSLEEP_THRESHOLD ? "You might be oversleeping. Too much sleep can also cause fatigue!"
                : extraSleep >= SLIGHT_OVERSLEEP_THRESHOLD ? "You slept longer than usual. If you're feeling tired despite this, check your sleep quality."
                : "You got a little extra sleep today. Make sure it doesn't affect your routine!";
    }
}

