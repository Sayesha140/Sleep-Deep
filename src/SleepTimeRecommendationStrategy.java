public abstract class SleepTimeRecommendationStrategy {

    private final int minSleep;
    private final int maxSleep;

    public SleepTimeRecommendationStrategy(int minSleep,int maxSleep){
        this.minSleep=minSleep;
        this.maxSleep=maxSleep;
    }

    public int getMinSleep() {
        return minSleep;
    }

    public int getMaxSleep() {
        return maxSleep;
    }

    public int getAdjustedMaxSleep(User user) {
        return maxSleep + (user.getActivityLevel() == ActivityLevel.HIGH ? 1 : 0);
    }

}
