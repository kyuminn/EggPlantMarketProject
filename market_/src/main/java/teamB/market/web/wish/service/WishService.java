package teamB.market.web.wish.service;

import java.util.List;

import teamB.market.domain.wish.Wish;

public interface WishService {
	int saveWish(Wish wish);
	List<Wish> selectMyWishList(long memberId);
	void remove (long itemId);
}
