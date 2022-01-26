package teamB.market.domain.wish.repository;

import java.util.List;

import teamB.market.domain.wish.Wish;

public interface WishRepository {
	int save(Wish wish);
	List<Wish> findByMemberId(long memberId);
	void delete(long itemId);
}
