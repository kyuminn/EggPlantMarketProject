package teamB.market.domain.item.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import teamB.market.domain.item.Category;
import teamB.market.domain.item.Item;

@Mapper
public interface ItemMapper {
	
	@Insert("insert into item(id,memberId,category,name,price,content,orderKey,filePath) "
			+ "values(item_seq.nextval,#{memberId},#{category},#{name},#{price},#{content},#{orderKey},#{filePath})")
    void save(Item item);
	
	@Select("select * from item where memberId=#{memberId}")
    Item findByMemberId(long memberId);
	
	@Select("select * from item order by id desc")
    List<Item> findAll();
	
	@Select("select * from item where id in (select itemId from shipping where shippingStatus='ONSALE')and rownum<=8 order by id desc")
	List<Item> findLatestItem();
    
    @Update("update item set category=#{item.category},name=#{item.name},price=#{item.price},content=#{item.content},filePath=#{item.filePath} where id=#{id}")
    void update(@Param("id")long id, @Param("item")Item updateParam);
    
    @Delete("delete from item where id=#{id}")
    void delete(long id);
    
    @Select("select * from item where id=#{id}")
    Item findById(long id);
    
    @Select("select * from item where orderKey=#{orderKey}")
    Item findByOrderKey(String orderKey);
    
    @Select("select * from item where name like '%' || #{keyword} || '%' and id in (select itemId from shipping where shippingStatus='ONSALE')")
    List<Item> findByKeyWord(String keyword);
    
    @Select("select * from item where category=#{category} and id in (select itemId from shipping where shippingStatus='ONSALE')")
    List<Item> findByCategory(Category category);
    
    @Select("select * from item where id in (select itemId from shipping where shippingStatus='ONSALE') order by hit desc")
    List<Item> findByHighHit();
    
    @Select("select * from item where id in (select itemId from shipping where shippingStatus='ONSALE') order by price")
    List<Item> findByLowPrice();
    
    @Select("select a.id,a.memberid,a.category,a.name,a.price,a.hit,a.content,a.orderKey,a.filePath from item a"
    		+ " join member b on a.memberId=b.id "
    		+ "where a.id in (select itemId from shipping where shippingStatus='ONSALE') "
    		+ "order by b.ratingAvg desc")
    List<Item> findByHighRate();
    
    @Update("update item set hit=hit+1 where id=#{id}")
    void updateHit(long id);
}
