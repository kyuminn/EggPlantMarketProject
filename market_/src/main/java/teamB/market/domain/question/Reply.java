package teamB.market.domain.question;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
    private long id;
    private long questionId;
    private long memberId;
    private String content;
}
