public class DaytimeFatigueTips implements ITipGenerator {
    @Override
    public String generateTips() {
        String[] tips = {
                "1.Ensure you're getting at least 7 to 9 hours of quality sleep.",
                "2.Try short power naps (20 to3 0 mins) if you're tired.",
                "3.Stay hydrated and eat balanced meals.",
                "4.Take breaks from screens and stretch often."
        };
        return String.join("\n", tips);
    }
}
