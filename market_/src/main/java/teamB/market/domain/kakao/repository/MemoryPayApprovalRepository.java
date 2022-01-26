package teamB.market.domain.kakao.repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Repository;

import teamB.market.domain.kakao.KakaoApprovalRequest;



@Repository
public class MemoryPayApprovalRepository implements PayApprovalRepository {
    private static Map<Long, KakaoApprovalRequest> store = new HashMap<>();
    private static long sequence = 0L;
    
    @Override
    public void save(KakaoApprovalRequest request) {
    	request.setId(++sequence);
        store.put(request.getId(), request);
    }

	@Override
	public KakaoApprovalRequest findByItemId(long itemId) {
		Iterator<Long> iter = store.keySet().iterator();
		KakaoApprovalRequest request = null;
		while(iter.hasNext()) {
			Long id = iter.next();
			if(store.get(id).getItemId()==itemId) {
				request = store.get(id);
			}
		}
		return request;
	}
}
