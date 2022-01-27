package teamB.market.web.item.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teamB.market.domain.item.Item;
import teamB.market.domain.item.mapper.ItemMapper;


@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;

	@Override
	public void save(Item item) {
		 itemMapper.save(item);
	}

	@Override
	public Item findByMemberId(long memberId) {
		return itemMapper.findByMemberId(memberId);
	}


	@Override
	public List<Item> findAll() {
		return itemMapper.findAll();
	}

	@Override
	public Item findById(long id) {
		return itemMapper.findById(id);
	}


	@Override
	public Item findByOrderKey(String orderKey) {
		return itemMapper.findByOrderKey(orderKey);
	}

	@Override
	public void delete(long id) {
		itemMapper.delete(id);
		
	}

	@Override
	public List<Item> findMyItemList(long memberId) {
		List<Item> ls = itemMapper.findAll();
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
		itemMapper.update(id, updateParam);
	}

	
	
}
