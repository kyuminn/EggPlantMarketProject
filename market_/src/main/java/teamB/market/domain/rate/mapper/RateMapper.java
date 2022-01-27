package teamB.market.domain.rate.mapper;

import org.apache.ibatis.annotations.Mapper;

import teamB.market.domain.rate.Rate;

@Mapper
public interface RateMapper {
	void save(Rate rate);
	Rate findByItemId(long itemId);
}
