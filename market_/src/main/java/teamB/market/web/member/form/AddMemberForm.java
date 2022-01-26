package teamB.market.web.member.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddMemberForm {
	
	//약관 동의 검증
	@AssertTrue
	private boolean provisionYn;
	
	@NotBlank
    private String name;
	
	@NotBlank
//    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
//    message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String pwd;
	
	@NotBlank
    private String confirmPwd;
	
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

    private String isEmailAuth;
    private int emailAuthCode;
}
