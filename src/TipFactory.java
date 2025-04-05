public class TipFactory {
    public static ITipGenerator getTipGenerator(SleepProblem problem) {
        switch (problem) {
            case INSOMNIA:
                return new InsomniaTips();
            case FREQUENT_WAKING:
                return new FrequentWakingTips();
            case TROUBLE_FALLING_ASLEEP:
                return new TroubleFallingAsleepTips();
            case DAYTIME_FATIGUE:
                return new DaytimeFatigueTips();
            case IRREGULAR_SLEEP_SCHEDULE:
                return new IrregularSleepScheduleTips();
            default:
                throw new IllegalArgumentException("Unknown sleep problem: " + problem);
        }
    }
}
