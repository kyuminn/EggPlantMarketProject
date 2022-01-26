package teamB.market.domain.kakao.repository;

import teamB.market.domain.kakao.KakaoApprovalRequest;


public interface PayApprovalRepository {
	public void save(KakaoApprovalRequest request);
	public KakaoApprovalRequest findByItemId(long itemId);
}
