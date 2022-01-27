package teamB.market.domain.shipping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import teamB.market.domain.shipping.Shipping;

@Mapper
public interface ShippingMapper {
	
	@Insert("insert into shipping(id,itemId,shippingStatus) "
			+ "values(shipping_seq.nextval,#{itemId},#{shippingStatus})")
	void save(Shipping shipping);
	
	@Select("select * from shipping where memberId=#{memberId}")
	List<Shipping> findByMemberId(long memberId);
	
	@Select("select * from shipping where itemId=#{itemId}")
	Shipping findByItemId(long itemId);
}
