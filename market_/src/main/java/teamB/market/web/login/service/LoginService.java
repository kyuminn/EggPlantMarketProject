package teamB.market.web.login.service;

import teamB.market.domain.member.Member;

public interface LoginService {
    Member login(String email, String password);
    boolean isAuthorizedEmail(String email);
    String findUserEmail(String name, String phoneNum);
    boolean isMemberExists(String email);
}
