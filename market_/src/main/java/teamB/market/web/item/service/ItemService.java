package teamB.market.web.item.service;

import java.util.List;

import teamB.market.domain.item.Item;
import teamB.market.domain.like.Like;

public interface ItemService {
	Item save(Item item);
	Item findByMemberId(long memberId);
	List<Item> findAll();
	Item findById(long id);
//	int saveLike(Like like);
	Item findByOrderKey(String orderKey);
	void delete(long id);
	
	List<Item> findMyItemList(long memberId);
	void updateItemDetail(long id, Item updateParam);

	
}
