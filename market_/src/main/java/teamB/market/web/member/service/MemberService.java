package teamB.market.web.member.service;


import teamB.market.domain.member.Member;

public interface MemberService {
    void join(Member member);
    void edit(long id, Member updateParam);
    Member findByEmail(String email);
    Member findByPhoneNum(String phoneNum);
    boolean authenticateEmail(String email,String code);
    void updatePwd(String email,String tmpPwd);
    Member findById(long id);
    void updateMemberInfo(long id, Member updateParam);
    void updateRole(long id);
    void delete(long memberId);
}
