package teamB.market.domain.like.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import teamB.market.domain.like.Like;

@Repository
public class MemoryLikeRepository implements LikeRepository {

    private static Map<Long, Like> store = new HashMap<>();
    private static long sequence = 0L;
	@Override
	public int save(Like like) {
		int result = -1;
		like.setId(++sequence);
		store.put(like.getId(), like);
		if(like!=null) {
			result=1;
		}
		return result;
	}
	@Override
	public List<Like> findByMemberId(long memberId) {
		List<Like> like = new ArrayList<>();
		Iterator<Long> iter = store.keySet().iterator();
		while(iter.hasNext()) {
			Long id = iter.next();
			if(store.get(id).getMemberId()==memberId) {
				like.add(store.get(id));
			}
		}
		return like;
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
