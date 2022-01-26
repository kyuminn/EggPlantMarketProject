package teamB.market.web.member.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SocialAddMemberForm {
	
	//약관 동의 검증
	@AssertTrue
	private boolean provisionYn;
	
	@NotBlank
    private String name;
	
	@NotBlank
    private String phoneNum;
	
	@NotBlank
    private String nickname;
	
	@NotBlank
	@Email
    private String email;
	
	@NotBlank
    private String postCode;
	
	@NotBlank
    private String roadAddr;
	
	@NotBlank
    private String detailAddr;

}
