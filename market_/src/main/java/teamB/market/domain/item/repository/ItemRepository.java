package teamB.market.domain.item.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import teamB.market.domain.item.Item;


public interface ItemRepository {
    Item save(Item item);
    Item findByMemberId(long memberId);
    List<Item> findAll();
    void update(long id, Item updateParam);
    void delete(long id);
    Item findById(long id);
    Item findByOrderKey(String orderKey);
}
