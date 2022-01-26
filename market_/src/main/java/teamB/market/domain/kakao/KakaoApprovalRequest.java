package teamB.market.domain.kakao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoApprovalRequest {
	private long id;
	private long itemId;
	// 구매자 아이디
	private long memberId;
	private String tid;
	
	//nullable
	private String pgToken;
	
}
