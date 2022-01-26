package teamB.market.web.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import teamB.market.domain.member.EmailAuth;
import teamB.market.domain.member.Member;
import teamB.market.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
	
    private final MemberRepository memberRepository;

    @Override
    public Member login(String email, String password) {
    	Member member = null;
    	if (memberRepository.findByEmail(email).getPwd().equals(password)) {
    		member = memberRepository.findByEmail(email);
    	}
        return member;
    }

	@Override
	public boolean isAuthorizedEmail(String email) {
		boolean isAuthorized = false;
		if (memberRepository.findByEmail(email).getIsEmailAuth().equals(EmailAuth.COMPLETE)) {
			isAuthorized = true;
		}
		return isAuthorized;
	}

	@Override
	public String findUserEmail(String name, String phoneNum) {
		String userEmail=null;
		if (memberRepository.findByPhoneNum(phoneNum).getName().equals(name)) {
			userEmail = memberRepository.findByPhoneNum(phoneNum).getEmail();
		}
		return userEmail;
	}

	@Override
	public boolean isMemberExists(String email) {
		boolean exist = false;
		if(memberRepository.findByEmail(email)!=null) {
			exist= true;
		}
		return exist;
	}

}
