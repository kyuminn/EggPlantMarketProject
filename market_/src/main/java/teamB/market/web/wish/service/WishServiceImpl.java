package teamB.market.web.wish.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import teamB.market.domain.wish.Wish;
import teamB.market.domain.wish.mapper.WishMapper;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService{
	
	
	private final WishMapper wishMapper;
	
	@Override
	public int save(Wish wish) {
		int result = -1;
		// 이미 찜한 상품인 경우 
		List<Wish> dbWish = wishMapper.findByMemberId(wish.getMemberId());
		
		// db에 저장된 찜 목록이 없을 때
		if(dbWish.isEmpty()) {
			result= wishMapper.save(wish);
		}
		
		// db에 저장된 찜 목록이 있을 때
		for (int i =0 ; i<dbWish.size(); i++) {
			if(dbWish.get(i).getItemId()==wish.getItemId()) {
				result = 0; // 이미 존재함
			}else {
				result= wishMapper.save(wish);
			}
		}

		return result;
	}
	
	@Override
	public List<Wish> selectMyWishList(long memberId) {
		List<Wish> myWishes = wishMapper.findByMemberId(memberId);
		return myWishes.isEmpty()? Collections.emptyList(): myWishes;
	}

	@Override
	public void remove(long itemId) {
		wishMapper.delete(itemId);
		
	}



}
