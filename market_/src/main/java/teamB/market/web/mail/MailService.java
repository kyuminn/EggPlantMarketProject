package teamB.market.web.mail;



import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
	
	private final JavaMailSender javaMailSender;
	
	//인증메일 보내기
	public void sendEmailAuthMail(String email,String authCode) {
		 MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
           MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
           mimeMessageHelper.setTo(email); // 메일 수신자
           mimeMessageHelper.setSubject("[가지마켓] 이메일 인증을 완료해주세요"); // 메일 제목
           mimeMessageHelper.setText("<html><head></head><body><div id=\"container\"><a href=\"http://localhost:8080/member/emailAuthCallBack?email="+email+"&code="+authCode+"\">링크를 누르면 이메일 인증이 완료됩니다</a></div></body></html>", true); // 메일 본문 내용, HTML 여부
           javaMailSender.send(mimeMessage);
           log.info("Success!!");
       } catch (MessagingException e) {
           log.info("fail!!");
           throw new RuntimeException(e);
       }
		
	}
	
	//임시 비밀번호 보내기
	public void sendTmpPwd(String email,String tmpPwd) {
		 MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
           MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
           mimeMessageHelper.setTo(email); // 메일 수신자
           mimeMessageHelper.setSubject("[가지마켓] 임시 비밀번호 "); // 메일 제목
           mimeMessageHelper.setText("<html><head></head><body><h2>임시 비밀번호:"+tmpPwd+"</h2><a href='http://localhost:8080/login'>로그인 하기</a></body></html>",true); // 메일 본문 내용, HTML 여부
           javaMailSender.send(mimeMessage);
           log.info("Success!!");
       } catch (MessagingException e) {
           log.info("fail!!");
           throw new RuntimeException(e);
       }
		
	}
}
