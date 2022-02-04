package teamB.market.web.login.service;

import teamB.market.domain.member.Member;

public interface LoginService {
	// 로그인
    Member login(String email, String password);
    
    // 이메일 인증 여부
    boolean isAuthorizedEmail(String email);
    
    // 이메일 찾기
    String findUserEmail(String name, String phoneNum);
    
    // 회원 존재 여부
    boolean isMemberExists(String email);
}
