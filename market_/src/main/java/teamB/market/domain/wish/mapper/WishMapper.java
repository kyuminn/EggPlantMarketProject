package teamB.market.domain.wish.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import teamB.market.domain.wish.Wish;

@Mapper
public interface WishMapper {
	
	@Insert("insert into wish(id,itemId,memberId) values(wish_seq.nextval,#{itemId},#{memberId})")
	int save(Wish wish);
	
	@Select("select * from wish where memberId=#{memberId}")
	List<Wish> findByMemberId(long memberId);
	
	@Delete("delete from wish where itemId=#{itemId}")
	void delete(long itemId);
}
