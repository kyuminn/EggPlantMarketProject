package teamB.market.domain.member.repository;


import teamB.market.domain.member.Member;

import java.util.List;

public interface  MemberRepository {
	
    Member save(Member member);
    Member findById(long id);
    Member findByEmail(String email);
    Member findByPhoneNum(String phoneNum);
    List<Member> findAll();
    void update(long id, Member updateParam);
    void delete(long id);

}
