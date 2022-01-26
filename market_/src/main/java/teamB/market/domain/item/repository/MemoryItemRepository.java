package teamB.market.domain.item.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import teamB.market.domain.item.Item;

@Repository
public class MemoryItemRepository implements  ItemRepository{
    private static Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;
    
    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public Item findByMemberId(long memberId) {
    	Iterator<Long> iter = store.keySet().iterator();
    	Item item = null;
    	while(iter.hasNext()) {
    		Long id = iter.next();
    		if(store.get(id).getMemberId()==memberId) {
    			item = store.get(id);
    		}
    	}
        return item;
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(long id, Item updateParam) {
    	Iterator<Long> iter = store.keySet().iterator();
    	while(iter.hasNext()) {
    		if(iter.next()==id) {
    			store.put(id, updateParam);
    		}
    	}
    }

    @Override
    public void delete(long id) {
    	Iterator<Long> iter = store.keySet().iterator();
    	while(iter.hasNext()) {
    		if(iter.next()==id) {
    			iter.remove();
    		}
    	}
    }

	@Override
	public Item findById(long id) {
		Iterator<Long> iter = store.keySet().iterator();
		Item item = null;
		while(iter.hasNext()) {
			if(iter.next()==id) {
				item = store.get(id);
			}
		}
		return item;
	}

	@Override
	public Item findByOrderKey(String orderKey) {
		Iterator<Long> iter = store.keySet().iterator();
		Item item = null;
		while(iter.hasNext()) {
			Long id = iter.next();
			if(store.get(id).getOrderKey().equals(orderKey)) {
				item = store.get(id);
			}
		}
		return item;
	}
}
