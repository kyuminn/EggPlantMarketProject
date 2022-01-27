package teamB.market.domain.item.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

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
    
    @Update("update item set category=#{item.category},name=#{item.name},price=#{item.price},content=#{item.content},filePath=#{item.filePath} where id=#{id}")
    void update(@Param("id")long id, @Param("item")Item updateParam);
    
    @Delete("delete from item where id=#{id}")
    void delete(long id);
    
    @Select("select * from item where id=#{id}")
    Item findById(long id);
    
    @Select("select * from item where orderKey=#{orderKey}")
    Item findByOrderKey(String orderKey);
    
    @Update("update item set hit=hit+1 where id=#{id}")
    void updateHit(long id);
}
