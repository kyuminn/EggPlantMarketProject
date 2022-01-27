package teamB.market.domain.rate.mapper;

import org.apache.ibatis.annotations.*;

import teamB.market.domain.rate.Rate;

@Mapper
public interface RateMapper {
	@Insert("insert into rate(id,itemId,memberId,rating) values(rate_seq.nextval,#{itemId},#{memberId},#{rating})")
	void save(Rate rate);
	
	@Select("select * from rate where itemId=#{itemId}")
	Rate findByItemId(long itemId);
}
