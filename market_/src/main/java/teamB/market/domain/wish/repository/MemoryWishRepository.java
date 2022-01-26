package teamB.market.domain.wish.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import teamB.market.domain.wish.Wish;

@Repository
public class MemoryWishRepository implements WishRepository {

    private static Map<Long, Wish> store = new HashMap<>();
    private static long sequence = 0L;
	@Override
	public int save(Wish wish) {
		int result = -1;
		wish.setId(++sequence);
		store.put(wish.getId(), wish);
		if(wish!=null) {
			result=1;
		}
		return result;
	}
	@Override
	public List<Wish> findByMemberId(long memberId) {
		List<Wish> wish = new ArrayList<>();
		Iterator<Long> iter = store.keySet().iterator();
		while(iter.hasNext()) {
			Long id = iter.next();
			if(store.get(id).getMemberId()==memberId) {
				wish.add(store.get(id));
			}
		}
		return wish;
	}
	@Override
	public void delete(long itemId) {
		Iterator<Long> iter = store.keySet().iterator();
		while(iter.hasNext()) {
			Long id = iter.next();
			if (store.get(id).getItemId()==itemId) {
				iter.remove();
			}
		}
		
	}
	
	
}
