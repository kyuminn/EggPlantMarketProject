package teamB.market.domain.member.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import teamB.market.domain.member.EmailAuth;
import teamB.market.domain.member.Member;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public void save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
    }
    
    @Override
    public Member findById(long id) {
    	Member member = store.get(id);
        return member;
    }

    @Override
    public Member findByEmail(String email) {
    	Member member = null;
    	Iterator<Long> iter= store.keySet().iterator();
    	while(iter.hasNext()) {
    		Long id = iter.next();
    		if(store.get(id).getEmail().equals(email)) {
    			member = store.get(id);
    		}
    	}
        return member;
    }

    @Override
    public Member findByPhoneNum(String phoneNum) {
    	Member member = null;
    	Iterator<Long> iter = store.keySet().iterator();
    	while(iter.hasNext()) {
    		Long id = iter.next();
    		if (store.get(id).getPhoneNum().equals(phoneNum)) {
    			member = store.get(id);
    		}
    	}
        return member;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(long id, Member updateParam) {
    	Iterator<Long> iter = store.keySet().iterator();
    	Member member = null;
    	while(iter.hasNext()) {
    		if(iter.next()==id) {
    			member = store.get(id);
    			member.setAddr(updateParam.getAddr());
    			member.setPhoneNum(updateParam.getPhoneNum());
    			member.setNickname(updateParam.getNickname());
    			member.setPwd(updateParam.getPwd()); // 비밀번호 변경하지 않았을 경우 서비스단에서 기존 비밀번호를 다시 넣어준다
    		}
    	}
    }

    @Override
    public void delete(long id) {
    	store.remove(id);
    }


    


}
