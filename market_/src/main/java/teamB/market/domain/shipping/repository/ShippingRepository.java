package teamB.market.domain.shipping.repository;

import java.util.List;

import teamB.market.domain.shipping.Shipping;

public interface ShippingRepository {
	void save(Shipping shipping);
	//Shipping findById(long id);
	List<Shipping> findByMemberId(long memberId);
	Shipping findByItemId(long itemId);
}
