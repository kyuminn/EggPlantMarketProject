package teamB.market.web.login.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginForm {
	
	@NotBlank
    private String email;
	@NotBlank
    private String password;
    private boolean rememberEmail;
}
