package teamB.market.domain.question;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    private long id;
    private long itemId;
    private long memberId;
    private String title;
    private String writer;
    private String content;
    private String currentTime;
    private IsReplied isReplied;
}
