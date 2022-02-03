package teamB.market.domain.kakao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoApprovalRequest {
	private long id;
	private long itemId;
	private long memberId;
	private String tid;
	private String pgToken;
	
}
