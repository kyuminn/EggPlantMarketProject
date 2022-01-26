package teamB.market.domain.item.repository;

import teamB.market.domain.item.Item;

import java.util.List;

public interface ItemRepository {
    Item save(Item item);
    Item findByMemberId(long memberId);
    List<Item> findAll();
    void update(long id, Item updateParam);
    void delete(long id);
    Item findById(long id);
    Item findByOrderKey(String orderKey);
}
