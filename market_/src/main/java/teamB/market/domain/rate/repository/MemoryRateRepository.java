package teamB.market.domain.rate.repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Repository;

import teamB.market.domain.rate.Rate;


@Repository
public class MemoryRateRepository implements RateRepository {
    private static Map<Long, Rate> store = new HashMap<>();
    private static long sequence = 0L;
	
    @Override
	public void save(Rate rate) {
		rate.setId(++sequence);
		store.put(rate.getId(), rate);
	}

	@Override
	public Rate findByItemId(long itemId) {
		Iterator<Long> iter = store.keySet().iterator();
		Rate rate = null;
		while(iter.hasNext()) {
			Long id = iter.next();
			if(store.get(id).getItemId()==itemId) {
				rate = store.get(id);
			}
		}
		return rate;
	}
}
