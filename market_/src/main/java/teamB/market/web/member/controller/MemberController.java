package teamB.market.web.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamB.market.domain.member.EmailAuth;
import teamB.market.domain.member.Member;
import teamB.market.domain.member.Route;
import teamB.market.web.mail.MailService;
import teamB.market.web.member.form.AddMemberForm;
import teamB.market.web.member.form.EditMemberForm;
import teamB.market.web.member.form.SocialAddMemberForm;
import teamB.market.web.member.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {
	

    private final MemberService memberService;
	private final MailService emailService;


    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("member", new AddMemberForm());
        return "member/addForm";
    }
    
    //소셜 회원가입
    // 어떤 소셜 경로로 들어왔는지 @RequestParam으로 받는것을... 고려해보기!
    @PostMapping("/socialAdd")
    @ResponseBody
    public String socialAdd(@Validated @ModelAttribute("member") SocialAddMemberForm form, BindingResult bindingResult,Model model,
    		@RequestParam(required=false)String access) {
    	
    	//@NotBlank와 같이 validation 라이브러리가 제공하는 유효성 검사
    	if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "member/socialAddForm";
        }
    	
    	// 폼에 입력한 이메일이 데이터베이스에 존재하는데 이메일 인증이 되지 않은 경우 삭제
    	Member findMember = memberService.findByEmail(form.getEmail());
    	if(findMember!=null) {
    		if (findMember.getIsEmailAuth().equals(EmailAuth.WAIT)) {
    			memberService.delete(findMember.getId());
    		}
    	}
    	
    	// Custom 유효성 검사
        // 이메일 중복 체크
        if (memberService.findByEmail(form.getEmail())!=null) {
        	bindingResult.rejectValue("email", "AlreadyExisting");
        	return "member/socialAddForm";
        }
        
        // 전화번호 중복 체크
        if(memberService.findByPhoneNum(form.getPhoneNum())!=null) {
        	bindingResult.rejectValue("phoneNum", "AlreadyExisting");
        	return "member/socialAddForm";
        }
    	
    	Member member = new Member();
    	// 어느 소셜 경로로 들어왔는지 수정
    	if(access.equals("kakao")) {
    		member.setRoute(Route.KAKAO);

    	}else if(access.equals("naver")) {
    		member.setRoute(Route.NAVER);
    	}

//    	Address addr = new Address();
//    	addr.setPostCode(form.getPostCode());
//    	addr.setRoadAddr(form.getRoadAddr());
//    	addr.setDetailAddr(form.getDetailAddr());
    	
    	//DB varchar2 type과 맞춰서 String 값 넣어주기
    	String addr= form.getPostCode()+"%"+form.getRoadAddr()+"%"+form.getDetailAddr();
    	member.setAddr(addr);
    	member.setEmail(form.getEmail());
    	member.setName(form.getName());
    	member.setNickname(form.getNickname());
    	//member.setRole(Role.BUYER);
    	member.setPhoneNum(form.getPhoneNum());
    	//소셜 회원가입의 경우 바로 이메일 인증상태 넣어주기
    	member.setIsEmailAuth(EmailAuth.COMPLETE);
    	
    	//소셜 멤버의 경우 임시 비밀번호 생성해서 넣어주기
    	String pwd = RandomStringUtils.randomAlphanumeric(8);
    	member.setPwd(pwd);
    	
    	memberService.join(member);
    	System.out.println(member);
    	
        // 회원가입 완료 alert 
        String alertMsg = "<script>alert('회원가입 완료!');location.href='/login';</script>";        
        return alertMsg;
    	//return "redirect:/login";
    }
    
    //일반 회원가입
    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("member") AddMemberForm form, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "member/addForm";
        }
        
        // 비밀번호가 일치하지 않으면 다시 입력폼으로 이동
        if(!form.getPwd().equals(form.getConfirmPwd())) {
        	bindingResult.rejectValue("confirmPwd", "notMatching"); 
        	log.info("errors={}", bindingResult); // log 확인
        	return "member/addForm";
        }
        
    	// 폼에 입력한 이메일이 데이터베이스에 존재하는데 이메일 인증이 되지 않은 경우 삭제
    	Member findMember = memberService.findByEmail(form.getEmail());
    	if(findMember!=null) {
    		if (findMember.getIsEmailAuth().equals(EmailAuth.WAIT)) {
    			memberService.delete(findMember.getId());
    		}
    	}
        
        // 이메일 중복 체크
        if (memberService.findByEmail(form.getEmail())!=null) {
        	bindingResult.rejectValue("email", "AlreadyExisting");
        	return "member/addForm";
        }
        
        // 전화번호 중복 체크
        if(memberService.findByPhoneNum(form.getPhoneNum())!=null) {
        	bindingResult.rejectValue("phoneNum", "AlreadyExisting");
        	return "member/addForm";
        }
        

    	
    	// 이메일 인증 코드 생성
        String emailAuthCode = RandomStringUtils.randomAlphanumeric(8);
    	
    	Member member = new Member();
    	
        member.setRoute(Route.NORMAL);
    	member.setEmail(form.getEmail());
    	member.setIsEmailAuth(EmailAuth.WAIT);
    	member.setName(form.getName());
    	member.setNickname(form.getNickname());
    	member.setPwd(form.getPwd());
    	//member.setRole(Role.BUYER); // default 값 BUYER
    	member.setEmailAuthCode(emailAuthCode);
    	member.setPhoneNum(form.getPhoneNum());
    	
    	//주소 객체 저장
//    	Address addr = new Address();
//    	addr.setPostCode(form.getPostCode());
//    	addr.setRoadAddr(form.getRoadAddr());
//    	addr.setDetailAddr(form.getDetailAddr());
    	String addr= form.getPostCode()+"%"+form.getRoadAddr()+"%"+form.getDetailAddr();
    	member.setAddr(addr);

    	
    	
    	
    	//db 에 저장
    	memberService.join(member);
    	System.out.println(member);
    	

    	
    	// 일반 회원가입의 경우 인증 이메일 전송
    	emailService.sendEmailAuthMail(form.getEmail(), emailAuthCode);
    	model.addAttribute("email",form.getEmail());
        // 이메일 인증 요청 페이지로 이동
        return "member/checkEmail";
    }

    // 이메일 인증 완료 Controller 구현하기
    @GetMapping("/emailAuthCallBack")
    public String emailAuthCallBack(@RequestParam("email")String email,@RequestParam("code")String emailAuthCode) {
    		if(memberService.authenticateEmail(email, emailAuthCode)) {
    			return "redirect:/login";
    		}
    	return "#"; //이메일 인증 실패시? 어디로 보내지?
    }


    @GetMapping("/edit/{id}")
    public String editForm(HttpServletRequest request, Model model,@PathVariable("id")long id){

        // session 에 저장된 회원정보 가져오기
    	HttpSession session=request.getSession(false);
    	String userEmail =(String)session.getAttribute("loginSession");
    	Member loginMember = memberService.findByEmail(userEmail);

    	EditMemberForm member = new EditMemberForm();
    	member.setNickname(loginMember.getNickname());
    	member.setPhoneNum(loginMember.getPhoneNum());
    	
    	// 주소를 가져와서 공백을 기준으로 우편번호 도로명주소 상세주소 나누기
    	String addr = loginMember.getAddr();
    	String[] addrSplit = addr.split("%");
    	member.setPostCode(addrSplit[0]);
    	member.setRoadAddr(addrSplit[1]);
    	member.setDetailAddr(addrSplit[2]);
    	
        // Model 에 회원객체 값 넣기
    	model.addAttribute("member", member);
    	model.addAttribute("id", id);
        return "member/editForm";
    }
    
    @ResponseBody
    @PostMapping("/edit/{id}")
    public String edit(@Validated @ModelAttribute("member") EditMemberForm form, BindingResult bindingResult,
    		@RequestParam("currentPhoneNum")String currentPhoneNum,@PathVariable("id")long id){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "member/editForm";
        }

        // 핸드폰 번호 중복 시 다시 입력폼으로 이동
        // 기존 휴대폰번호와 다른 번호를 입력했을때만 중복 여부 체크
        if(!form.getPhoneNum().equals(currentPhoneNum)) {
        	if(memberService.findByPhoneNum(form.getPhoneNum())!=null) {
        		bindingResult.rejectValue("phoneNum", "AlreadyExisting");
        		return "member/editForm";
        	}
        }
        
        // 비밀번호가 일치하지 않으면 다시 입력폼으로 이동
        Member member = memberService.findByPhoneNum(currentPhoneNum);
         // 비밀번호 변경했을때만 에러 메세지 보여줌
        if(!form.getCurrentPwd().trim().equals("")) {
            if(!form.getCurrentPwd().trim().equals(member.getPwd())) {
            	bindingResult.rejectValue("currentPwd","notMatching");
            	return "member/editForm";
            }
        }

        
        // 변경 비밀번호 확인이 다시 입력폼으로 이동
        if(!form.getChangePwd().equals(form.getConfirmChangePwd())) {
        	bindingResult.rejectValue("confirmChangePwd", "notMatching");
        	return "member/editForm";
        }
        
        // 성공 로직
//        Address addr = new Address();
//        addr.setPostCode(form.getPostCode());
//        addr.setRoadAddr(form.getRoadAddr());
//        addr.setDetailAddr(form.getDetailAddr());
        Member updateParam = new Member();
        
    	String addr= form.getPostCode()+"%"+form.getRoadAddr()+"%"+form.getDetailAddr();
        updateParam.setAddr(addr);
        updateParam.setPhoneNum(form.getPhoneNum());
        updateParam.setNickname(form.getNickname());
        updateParam.setPwd(form.getChangePwd());
        
        memberService.updateMemberInfo(id, updateParam);
        // 수정 완료 alert 
        String alertMsg = "<script>alert('회원정보가 변경되었습니다!');history.go(-1);</script>";        
        //return "redirect:/member/edit/"+id;
        return alertMsg;
    }
    
    @GetMapping("/myPage")
    public String viewMyPage(HttpSession session,Model model) {
    	String email = (String)session.getAttribute("loginSession");
    	Member member= memberService.findByEmail(email);
    	model.addAttribute("loginMember", member);
    	
    	// 평점 넘겨주기
    	model.addAttribute("ratingAvg", member.getRatingAvg());
    	return "member/myPage";
    }
    
    @GetMapping("/myPoint/{id}")
    public String viewMyPoint(@PathVariable("id")Long memberId,Model model) {
    	Member member = memberService.findById(memberId);
    	model.addAttribute("loginMember", member);
    	return "member/myPoint";
    }
    
    @PostMapping("/convertPoint/{id}")
    public String convertMyPoint(@PathVariable("id")Long memberId) {
    	Member member = memberService.findById(memberId);
    	// 멤버의 포인트 0으로 초기화
    	member.setPoint(0);
    	return "redirect:/member/myPoint/"+memberId;
    }
}
