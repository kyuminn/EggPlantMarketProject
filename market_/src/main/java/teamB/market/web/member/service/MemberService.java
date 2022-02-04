package teamB.market.web.member.service;


import teamB.market.domain.member.Member;

public interface MemberService {
	// 회원가입
    void join(Member member);
    
    // 이메일로 찾기
    Member findByEmail(String email);
    
    // 연락처로 찾기
    Member findByPhoneNum(String phoneNum);
    
    // 이메일 인증하기
    boolean authenticateEmail(String email,String code);
    
    // 비밀번호 변경
    void updatePwd(String email,String tmpPwd);
    
    // 회원 아이디로 검색
    Member findById(long id);
    
    // 회원정보 수정
    void updateMemberInfo(long id, Member updateParam);
    
    // 판매자 업데이트
    void updateRole(long id);
    
    // 가지포인트 업데이트
    void updatePoint(int point, long id);
    
    // 판매자 평점 업데이트
    void updateRatingAvg(float avg, long id);
    
    // 회원 삭제
    void delete(long memberId);
}
