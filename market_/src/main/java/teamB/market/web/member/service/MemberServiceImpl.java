package teamB.market.web.member.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamB.market.domain.member.EmailAuth;
import teamB.market.domain.member.Member;
import teamB.market.domain.member.repository.MemberRepository;



@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
	@Autowired
    private final MemberRepository memberRepository;
    
    // 메일 전송
    private final JavaMailSender javaMailSender;

    @Override
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void edit(long id, Member updateParam) {
        memberRepository.update(id, updateParam);
    }
    
	@Override
	public Member findByEmail(String email) {
		Member member = memberRepository.findByEmail(email);
		return member;
	}

	@Override
	public Member findByPhoneNum(String phoneNum) {
		Member member = memberRepository.findByPhoneNum(phoneNum);
		return member;
	}

	@Override
	public boolean authenticateEmail(String email, String code) {
		boolean match = false;
		Member member = memberRepository.findByEmail(email);
		if(member.getEmailAuthCode().equals(code)) {
			member.setIsEmailAuth(EmailAuth.COMPLETE);
			match=true;
		}
		return match;
	}

	@Override
	public void updatePwd(String email,String tmpPwd) {
		Member member = memberRepository.findByEmail(email);
		member.setPwd(tmpPwd);
	}

	@Override
	public Member findById(long id) {
		return memberRepository.findById(id);
	}

	@Override
	public void updateMemberInfo(long id, Member updateParam) {
		
		//비밀번호 변경하지 않았으면 기존 패스워드로 설정해주기
		if(updateParam.getPwd()==null || updateParam.getPwd().equals("")) {
			updateParam.setPwd(memberRepository.findById(id).getPwd());
		}
		memberRepository.update(id, updateParam);
	}

	@Override
	public void delete(long memberId) {
		memberRepository.delete(memberId);
	}






}
