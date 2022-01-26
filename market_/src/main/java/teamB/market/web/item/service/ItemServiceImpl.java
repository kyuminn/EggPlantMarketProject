package teamB.market.web.item.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teamB.market.domain.item.Item;
import teamB.market.domain.item.repository.ItemRepository;


@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Item save(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item findByMemberId(long memberId) {
		return itemRepository.findByMemberId(memberId);
	}


	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public Item findById(long id) {
		return itemRepository.findById(id);
	}


	@Override
	public Item findByOrderKey(String orderKey) {
		return itemRepository.findByOrderKey(orderKey);
	}

	@Override
	public void delete(long id) {
		itemRepository.delete(id);
		
	}

	@Override
	public List<Item> findMyItemList(long memberId) {
		List<Item> ls = itemRepository.findAll();
		List<Item> sellerLs= new ArrayList<Item>();
		for(int i=0; i<ls.size(); i++) {
			if(ls.get(i).getMemberId()==memberId) {
				sellerLs.add(ls.get(i));
			}
		}
		return sellerLs;
	}

	@Override
	public void updateItemDetail(long id, Item updateParam) {
		itemRepository.update(id, updateParam);
	}

	
	
}
