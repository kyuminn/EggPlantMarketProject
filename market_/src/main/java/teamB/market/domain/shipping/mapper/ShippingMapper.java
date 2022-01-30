package teamB.market.domain.shipping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import teamB.market.domain.shipping.Shipping;
import teamB.market.domain.shipping.Status;

@Mapper
public interface ShippingMapper {
	
	@Insert("insert into shipping(id,itemId,shippingStatus) "
			+ "values(shipping_seq.nextval,#{itemId},#{shippingStatus})")
	void save(Shipping shipping);
	
	@Select("select * from shipping where memberId=#{memberId}")
	List<Shipping> findByMemberId(long memberId);
	
	@Select("select * from shipping where itemId=#{itemId}")
	Shipping findByItemId(long itemId);
	
	@Update("update shipping set shippingStatus=#{status} where id = #{id}")
	void updateShippingStatus(@Param("id")long id,@Param("status")Status status);
	
	@Update("update shipping set memberId=#{memberId} where id=#{id}")
	void updateBuyerId(@Param("memberId")long memberId, @Param("id")long id);
}
