package teamB.market.web.socialAPI.kakao;

import lombok.Getter;
import lombok.Setter;

// response data를 java Object애 mapping하기 위한 클래스

@Getter @Setter
public class KakaoPay {
	private String tid;
	private Boolean tms_result;
	private String next_redirect_app_url;
	private String next_redirect_mobile_url;
	private String next_redirect_pc_url;
	private String android_app_scheme;
	private String ios_app_scheme;
	private String created_at;
}
