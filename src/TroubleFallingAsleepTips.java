public class TroubleFallingAsleepTips implements ITipGenerator {
    @Override
    public String generateTips() {
        String[] tips = {
                "1.Avoid screen time 1 hour before bed.",
                "2.Try reading or light stretching before sleeping.",
                "3.Go to bed only when sleepy.",
                "4.Avoid heavy meals or spicy food at night."
        };
        return String.join("\n", tips);
    }
}
