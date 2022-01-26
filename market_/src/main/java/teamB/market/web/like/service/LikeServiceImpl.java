package teamB.market.web.like.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import teamB.market.domain.like.Like;
import teamB.market.domain.like.repository.LikeRepository;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{
	
	
	private final LikeRepository likeRepository;
	
	@Override
	public int saveLike(Like like) {
		int result = -1;
		// 이미 찜한 상품인 경우 
		List<Like> dbLike = likeRepository.findByMemberId(like.getMemberId());
		
		// db에 저장된 찜 목록이 없을 때
		if(dbLike.isEmpty()) {
			result= likeRepository.save(like);
		}
		
		// db에 저장된 찜 목록이 있을 때
		for (int i =0 ; i<dbLike.size(); i++) {
			if(dbLike.get(i).getItemId()==like.getItemId()) {
				result = 0; // 이미 존재함
			}else {
				result= likeRepository.save(like);
			}
		}

		return result;
	}
	
	@Override
	public List<Like> selectMyLikeList(long memberId) {
		List<Like> myLikes = likeRepository.findByMemberId(memberId);
		return myLikes.isEmpty()? Collections.emptyList(): myLikes;
	}

	@Override
	public void remove(long itemId) {
		likeRepository.delete(itemId);
		
	}



}
