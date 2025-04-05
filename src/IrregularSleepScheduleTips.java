public class IrregularSleepScheduleTips implements ITipGenerator {
    @Override
    public String generateTips() {
        String[] tips = {
                "1.Set a fixed bedtime and wake-up time (even on weekends).",
                "2.Expose yourself to natural sunlight in the morning.",
                "3.Avoid naps that are too long or late in the day.",
                "4.Use alarms and reminders to build consistency."
        };
        return String.join("\n", tips);
    }
}

