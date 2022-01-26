package teamB.market.web.like.service;

import java.util.List;

import teamB.market.domain.like.Like;

public interface LikeService {
	int saveLike(Like like);
	List<Like> selectMyLikeList(long memberId);
	void remove (long itemId);
}
