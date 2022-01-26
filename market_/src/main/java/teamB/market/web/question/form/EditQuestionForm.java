package teamB.market.web.question.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class EditQuestionForm {

    private long itemId;

    @NotBlank
    private String title;

    @NotBlank
    private String date;

    @NotBlank
    private String writer;

    @NotBlank
    private String content;
}
