package teamB.market.domain.like.repository;

import java.util.List;

import teamB.market.domain.like.Like;

public interface LikeRepository {
	int save(Like like);
	List<Like> findByMemberId(long memberId);
	void delete(long itemId);
}
