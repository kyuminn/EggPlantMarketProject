package teamB.market.web.member.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamB.market.domain.member.EmailAuth;
import teamB.market.domain.member.Member;
import teamB.market.domain.member.mapper.MemberMapper;



@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
    

    @Override
    public void join(Member member) {
    	memberMapper.save(member);
    }
    
	@Override
	public Member findByEmail(String email) {
		Member member = memberMapper.findByEmail(email);
		return member;
	}

	@Override
	public Member findByPhoneNum(String phoneNum) {
		Member member = memberMapper.findByPhoneNum(phoneNum);
		return member;
	}

	@Override
	public boolean authenticateEmail(String email, String code) {
		boolean match = false;
		Member member = memberMapper.findByEmail(email);
		if(member.getEmailAuthCode().equals(code)) {
			member.setIsEmailAuth(EmailAuth.COMPLETE);
			memberMapper.updateIsEmailAuth(member.getId(), member);
			match=true;
		}
		return match;
	}

	@Override
	public void updatePwd(String email,String tmpPwd) {
		Member member = memberMapper.findByEmail(email);
		memberMapper.updatePwd(member.getId(), tmpPwd);
	}

	@Override
	public Member findById(long id) {
		return memberMapper.findById(id);
	}

	@Override
	public void updateMemberInfo(long id, Member updateParam) {
		
		//비밀번호 변경하지 않았으면 기존 패스워드로 설정해주기
		if(updateParam.getPwd()==null || updateParam.getPwd().equals("")) {
			updateParam.setPwd(memberMapper.findById(id).getPwd());
		}
		memberMapper.update(id, updateParam);
	}

	@Override
	public void delete(long memberId) {
		memberMapper.delete(memberId);
	}

	@Override
	public void updateRole(long id) {
		memberMapper.updateRole(id);
		
	}

	@Override
	public void updatePoint(int point, long id) {
		memberMapper.updatePoint(point, id);
		
	}

	@Override
	public void updateRatingAvg(float avg, long id) {
		memberMapper.updateRatingAvg(avg, id);
		
	}






}
