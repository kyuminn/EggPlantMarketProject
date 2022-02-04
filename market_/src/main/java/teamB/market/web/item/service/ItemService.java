package teamB.market.web.item.service;

import java.util.List;

import teamB.market.domain.item.Category;
import teamB.market.domain.item.Item;


public interface ItemService {
	// 저장
	void save(Item item);
	
	// 판매자 시퀀스로 검색
	Item findByMemberId(long memberId);
	
	// 아이템 전체 검색
	List<Item> findAll();
	
	// 아이템 시퀀스로 검색
	Item findById(long id);
	
	// 주문번호로 검색
	Item findByOrderKey(String orderKey);
	
	// 아이템 삭제
	void delete(long id);
	
	// 나의 판매글 목록
	List<Item> findMyItemList(long memberId);
	
	// 아이템 수정
	void updateItemDetail(long id, Item updateParam);
	
	// 메인 페이지에 보여줄 아이템 리스트
	List<Item> selectMainItemList();
	
	// 헤더 검색창 - 키워드로 검색
	List<Item> findByKeyword(String keyword);
	
	// 카테고리별로 보기
	List<Item> findByCategory(String category);
	
	// 조건부 검색
	List<Item> findByCondition(String condition);
	
	// 조회수 +1
	void updateHit(long id);

	
}
