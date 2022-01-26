package teamB.market.web.login.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FindEmailForm {
	@NotBlank
    private String name;
	@NotBlank
    private String phoneNum;
}
