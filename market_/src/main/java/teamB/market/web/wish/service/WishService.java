package teamB.market.web.wish.service;

import java.util.List;

import teamB.market.domain.wish.Wish;

public interface WishService {
	// 찜 저장
	int save(Wish wish);
	
	// 특정 회원의 찜 목록 불러오기
	List<Wish> selectMyWishList(long memberId);
	
	// 찜 목록에서 제거
	void remove (long itemId);
}
