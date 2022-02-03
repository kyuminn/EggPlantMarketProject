package teamB.market.web.login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamB.market.web.login.form.FindEmailForm;
import teamB.market.web.login.form.FindPwdForm;
import teamB.market.web.login.form.LoginForm;
import teamB.market.web.login.service.LoginService;
import teamB.market.web.mail.MailService;
import teamB.market.web.member.service.MemberService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
	
    private final LoginService loginService;
	private final MemberService memberService;
	private final MailService mailService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginFormData")LoginForm form,@CookieValue(value="rememberEmail",required=false)Cookie emailCookie){
    	// 이메일 기억하기 쿠기 값이 있는 경우
    	if (emailCookie!=null) {
    		form.setEmail(emailCookie.getValue());
    		form.setRememberEmail(true);
    	}
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginFormData") LoginForm form, BindingResult bindingResult,
    		HttpSession session,HttpServletResponse response,@RequestParam(defaultValue="/") String redirectURL){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "login/loginForm";
        }
        
        // 존재하지 않는 회원일 경우 입력폼으로 이동
        if(!loginService.isMemberExists(form.getEmail())) {
        	bindingResult.reject("NotExistingMember");
        	return "login/loginForm";
        }
        // 이메일 또는 비밀번호가 일치하지 않을 시 다시 입력폼으로 이동
        if(loginService.login(form.getEmail(), form.getPassword())==null) {
        	bindingResult.reject("EmailPwdNotMatching");
        	return "login/loginForm";
        }

        // 이메일 인증 미진행 시 다시 입력폼으로 이동
        if(!loginService.isAuthorizedEmail(form.getEmail())) {
        	bindingResult.reject("Unauthorized");
        	return "login/loginForm";
        }
        
        // 이메일 기억하기 쿠키 구현
        Cookie cookie = new Cookie("rememberEmail",form.getEmail());
        cookie.setPath("/");
        if(form.isRememberEmail()) {
        	cookie.setMaxAge(60*60*24);
        }else {
        	cookie.setMaxAge(0);
        }
        response.addCookie(cookie);
        
        // 성공 로직 (session 에 회원 이메일 정보 저장)
        session.setAttribute("loginSession", form.getEmail());

        return "redirect:"+redirectURL;
    }


    // 네이버 계정으로 로그인 구현해보기
    
    
    
    
    

    @GetMapping("/login/find/email")
    public String findEmailForm(@ModelAttribute("findEmailFormData")FindEmailForm form){
        return "login/findEmailForm";
    }

    @PostMapping("/login/find/email")
    public String findEmail(@Validated @ModelAttribute("findEmailFormData")FindEmailForm form, BindingResult bindingResult,Model model){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "login/findEmailForm";
        }
        String userEmail = loginService.findUserEmail(form.getName(), form.getPhoneNum());
        // 핸드폰 번호 & 이름이 일치하지 않을 시 다시 입력폼으로 이동
        if (userEmail==null) {
        	bindingResult.reject("NotExistingMember");
        	return "login/findEmailForm";
        }
        // 일치하면 이메일 값 가져와서 모델에 넣어주기
        model.addAttribute("userEmail",userEmail);
        
        return "login/findEmail";
    }

    @GetMapping("/login/find/pwd")
    public String findPwdForm(@ModelAttribute("findPwdFormData")FindPwdForm form){
        return "login/findPwdForm";
    }

    @PostMapping("/login/find/pwd")
    public String findPwd(@Validated @ModelAttribute("findPwdFormData")FindPwdForm form, BindingResult bindingResult,Model model){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "login/findPwdForm";
        }

        // 이메일이 존재하지 않을 시 다시 입력폼으로 이동
          if(!loginService.isMemberExists(form.getEmail())) {
        	bindingResult.reject("NotExistingMember");
        	return "login/findPwdForm";
        }

        // 성공 로직 (이메일로 임시 비밀번호 전송)
        // 8글자의 영대소문자+아라비아 숫자로 구성된 무작위 문자열 반환
       String tmpPwd = RandomStringUtils.randomAlphanumeric(8);
       
       //메일로 임시 비밀번호 전송
       mailService.sendTmpPwd(form.getEmail(),tmpPwd);
   		
   		// db에 비밀번호 업데이트 해주기
   		memberService.updatePwd(form.getEmail(), tmpPwd);
   		model.addAttribute("userEmail",form.getEmail());
        // 비밀번호 전송했음 페이지 보여주기
        return "login/findPwd";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }

}
