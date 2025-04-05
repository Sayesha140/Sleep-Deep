public class InsomniaTips implements ITipGenerator {
    @Override
    public String generateTips() {
        String[] tips = {
                "1.Avoid caffeine in the evening.",
                "2.Stick to a consistent sleep schedule.",
                "3.Establish a calming bedtime routine.",
                "4.Try relaxing techniques like meditation or deep breathing."
        };
        return String.join("\n", tips);
    }
}
