package teamB.market.domain.rate.repository;

import teamB.market.domain.rate.Rate;

public interface RateRepository {
	void save(Rate rate);
	Rate findByItemId(long itemId);
}
