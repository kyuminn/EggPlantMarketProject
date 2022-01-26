package teamB.market.web.wish.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import teamB.market.domain.wish.Wish;
import teamB.market.domain.wish.repository.WishRepository;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService{
	
	
	private final WishRepository wishRepository;
	
	@Override
	public int saveWish(Wish wish) {
		int result = -1;
		// 이미 찜한 상품인 경우 
		List<Wish> dbWish = wishRepository.findByMemberId(wish.getMemberId());
		
		// db에 저장된 찜 목록이 없을 때
		if(dbWish.isEmpty()) {
			result= wishRepository.save(wish);
		}
		
		// db에 저장된 찜 목록이 있을 때
		for (int i =0 ; i<dbWish.size(); i++) {
			if(dbWish.get(i).getItemId()==wish.getItemId()) {
				result = 0; // 이미 존재함
			}else {
				result= wishRepository.save(wish);
			}
		}

		return result;
	}
	
	@Override
	public List<Wish> selectMyWishList(long memberId) {
		List<Wish> myWishes = wishRepository.findByMemberId(memberId);
		return myWishes.isEmpty()? Collections.emptyList(): myWishes;
	}

	@Override
	public void remove(long itemId) {
		wishRepository.delete(itemId);
		
	}



}
