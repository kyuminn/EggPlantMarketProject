package teamB.market.web.login.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import teamB.market.domain.member.EmailAuth;
import teamB.market.domain.member.Member;
import teamB.market.domain.member.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{
	
	private final MemberMapper memberMapper;

    @Override
    public Member login(String email, String password) {
    	Member member = null;
    	if (memberMapper.findByEmail(email).getPwd().equals(password)) {
    		member = memberMapper.findByEmail(email);
    	}
        return member;
    }

	@Override
	public boolean isAuthorizedEmail(String email) {
		boolean isAuthorized = false;
		if (memberMapper.findByEmail(email).getIsEmailAuth().equals(EmailAuth.COMPLETE)) {
			isAuthorized = true;
		}
		return isAuthorized;
	}

	@Override
	public String findUserEmail(String name, String phoneNum) {
		String userEmail=null;
		Member member = memberMapper.findByPhoneNum(phoneNum);
		if (member!=null) {
			if (member.getName().equals(name)) {
				userEmail= member.getEmail();
			}
		}
		return userEmail;
	}

	@Override
	public boolean isMemberExists(String email) {
		boolean exist = false;
		if(memberMapper.findByEmail(email)!=null) {
			exist= true;
		}
		return exist;
	}

}
