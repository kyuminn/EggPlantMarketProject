package teamB.market.domain.rate.mapper;

import org.apache.ibatis.annotations.*;

import teamB.market.domain.rate.Rate;

@Mapper
public interface RateMapper {
	@Insert("insert into rate(id,itemId,memberId,rating) values(rate_seq.nextval,#{itemId},#{memberId},#{rating})")
	void save(Rate rate);
	
	// 특정 아이템에 대해 받은 평점 보여주기
	@Select("select * from rate where itemId=#{itemId}")
	Rate findByItemId(long itemId);
	
	// 판매자 평점 평균 보여주기
	@Select("select avg(rating) from rate where itemId in (select id from item where memberId=#{sellerId})")
	float selectRatingAvg(long sellerId);
	
}
