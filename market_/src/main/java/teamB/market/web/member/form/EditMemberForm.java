package teamB.market.web.member.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EditMemberForm {
    private String nickname;
    @NotBlank
    private String phoneNum;
    //private String addr;
	@NotBlank
    private String postCode;
	
	@NotBlank
    private String roadAddr;
	
	@NotBlank
    private String detailAddr;
	
    private String currentPwd;
    private String changePwd;
    private String confirmChangePwd;
}
