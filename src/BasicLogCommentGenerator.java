import java.time.LocalDate;

public class BasicLogCommentGenerator {

    private final ISleepLogAnalyzer analyzer;
    private final SleepTimeRecommendationStrategy recommendationStrategy;
    private User user;

    private static final double SEVERE_OVERSLEEP_THRESHOLD = 4.0;
    private static final double MODERATE_OVERSLEEP_THRESHOLD = 2.0;
    private static final double SLIGHT_OVERSLEEP_THRESHOLD = 1.0;

    public BasicLogCommentGenerator(ISleepLogAnalyzer analyzer, SleepTimeRecommendationStrategy recommendationStrategy,User user) {
        this.analyzer=analyzer;
        this.recommendationStrategy = recommendationStrategy;
        this.user=user;
    }

    public String generateLogComment(LocalDate date) {
        double totalTimeSlept = analyzer.calculateTotalSleepDurationByDate(date);
        int minSleep = recommendationStrategy.getMinSleep();
        int maxSleep = recommendationStrategy.getAdjustedMaxSleep(user);
        double extraSleep = totalTimeSlept - maxSleep;

        return extraSleep > 0 ? getOverSleepingComment(extraSleep) :
                totalTimeSlept >= minSleep ? "Great job! You slept just perfect today! Keep it up!" :
                        totalTimeSlept >= minSleep - 1 ? "Almost there! Try to sleep a little longer." :
                                "You need more rest. Consider adjusting your sleep schedule.";
    }

    private String getOverSleepingComment(double extraSleep) {
        return extraSleep >= SEVERE_OVERSLEEP_THRESHOLD ? "Are you okay? Oversleeping sometimes may indicate underlying health issues."
                : extraSleep >= MODERATE_OVERSLEEP_THRESHOLD ? "You might be oversleeping. Too much sleep can also cause fatigue!"
                : extraSleep >= SLIGHT_OVERSLEEP_THRESHOLD ? "You slept longer than usual. If you're feeling tired despite this, check your sleep quality."
                : "You got a little extra sleep today. Make sure it doesn't affect your routine!";
    }
}

