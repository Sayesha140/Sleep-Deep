import java.util.List;
import java.util.Random;

public class QuickTipGenerator implements ITipGenerator{
    private final ITipReader tipReader;
    private final Random random=new Random();

    public QuickTipGenerator(ITipReader tipReader){
        this.tipReader=tipReader;
    }

    @Override
    public String generateTips() {
        List<String> tips=tipReader.loadTips();

        if (tips.isEmpty()){
            return "No tips available for now.";
        }
        return tips.get(random.nextInt(tips.size()));
    }
}
