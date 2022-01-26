package teamB.market.domain.shipping.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import teamB.market.domain.shipping.Shipping;

@Repository
public class MemoryShippingRepository implements ShippingRepository{
	
    private static Map<Long, Shipping> store = new HashMap<>();
    private static long sequence = 0L;
    
	@Override
	public void save(Shipping shipping) {
        shipping.setId(++sequence);
        store.put(shipping.getId(), shipping);
		
	}

	@Override
	public List<Shipping> findByMemberId(long memberId) {
		List<Shipping> ls = new ArrayList<Shipping>();
		Iterator<Long> iter = store.keySet().iterator();
		while(iter.hasNext()) {
			Long id = iter.next();
			if (store.get(id).getMemberId()==memberId) {
				ls.add(store.get(id));
			}
		}
		return ls;
	}

	@Override
	public Shipping findByItemId(long itemId) {
		Shipping shipping = null;
		Iterator<Long> iter = store.keySet().iterator();
		while(iter.hasNext()) {
			Long id = iter.next();
			if(store.get(id).getItemId()==itemId) {
				shipping = store.get(id);
			}
		}
		return shipping;
	}

//	@Override
//	public Shipping findById(long id) {
//		Iterator<Long> iter = store.keySet().iterator();
//		Shipping shipping = null;
//		while(iter.hasNext()) {
//			if(iter.next()==id) {
//				shipping = store.get(id);
//			}
//		}
//		return shipping;
//	}


}
