public class FrequentWakingTips implements ITipGenerator {
    @Override
    public String generateTips() {
        String[] tips = {
                "1.Limit liquid intake before bed.",
                "2.Keep your bedroom cool, quiet, and dark.",
                "3.Use white noise to avoid sudden noises waking you.",
                "4.Evaluate for sleep apnea or medical conditions if frequent."
        };
        return String.join("\n", tips);
    }
}
