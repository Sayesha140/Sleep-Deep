import java.time.LocalDate;

public interface ISleepLogCommentGenerator {
    String generateLogComment(LocalDate date);
}