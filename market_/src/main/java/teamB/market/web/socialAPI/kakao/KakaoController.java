package teamB.market.web.socialAPI.kakao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;
import teamB.market.domain.item.Item;
import teamB.market.domain.kakao.KakaoApprovalRequest;
import teamB.market.domain.kakao.mapper.KakaoPayMapper;
import teamB.market.domain.member.Member;
import teamB.market.domain.shipping.Shipping;
import teamB.market.domain.shipping.Status;
import teamB.market.domain.shipping.mapper.ShippingMapper;
import teamB.market.web.item.service.ItemService;
import teamB.market.web.member.form.SocialAddMemberForm;
import teamB.market.web.member.service.MemberService;
import teamB.market.web.socialAPI.kakao.approval.ApprovalResponse;




@Controller
@RequiredArgsConstructor
public class KakaoController {
	

	private final MemberService memberService;
	private final ItemService itemService;
	private final KakaoPayMapper kakaoPayMapper;
	private final ShippingMapper shippingMapper;
	
	//카카오 로그인
	@GetMapping("/myapp/loginCallBack")
	public String loginProc(@RequestParam("code")String code,Model model,HttpSession session) {

		String accessToken = getAccessToken(code);
		HashMap<String,Object> userInfo = getUserInfo(accessToken);
		String userEmail = (String)userInfo.get("email");
		
		// 회원가입이 되어 있지 않으면 소셜 회원 가입 페이지로 보냄
		if(memberService.findByEmail(userEmail)==null) {
			model.addAttribute("email",userInfo.get("email"));
			model.addAttribute("accessToken", accessToken);
			model.addAttribute("nickname",userInfo.get("nickName"));
			model.addAttribute("member",new SocialAddMemberForm());
			model.addAttribute("access","kakao");
			return "member/socialAddForm";
		}
		
		// 회원가입이 되어있으면 세션에 값을 저장하고 메인 페이지로 리다이렉트
		
		session.setAttribute("loginSession", userEmail);
		return "redirect:/";
	}
	
	//토큰 값 가져오기
		public String getAccessToken(String code) {
			String access_Token="";
			String refresh_Token="";
			String reqURL="https://kauth.kakao.com/oauth/token";
			
			try {
				URL url = new URL(reqURL);
				HttpURLConnection connection =(HttpURLConnection)url.openConnection();
				//HttpURLConnection 객체를 통해 url에 값을 입/출력 할 수 있음
				connection.setRequestMethod("POST");
				//POST요청 시 필요한 설정
				connection.setDoOutput(true);
				
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
				StringBuilder builder= new StringBuilder();
				builder.append("grant_type=authorization_code");
				builder.append("&client_id=f98f37d47a62be09db4e5ccfed265b61");
				builder.append("&redirect_uri=http://localhost:8080/myapp/loginCallBack");
				builder.append("&code="+code);
				
				bw.write(builder.toString());
				bw.flush();
				
				// 요청을 통해 얻은 json 타입의  Response 메시지 읽어오기
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line="";
				String result="";
				
				while((line = br.readLine()) !=null) {
					result+=line;
				}
				
				System.out.println("responseBody"+result);
				
				//Gson 라이브러리에 포함된 클래스로 json 데이터 읽어들이기
				JsonElement element = JsonParser.parseString(result);
				//json data 읽어들잉기
				access_Token = element.getAsJsonObject().get("access_token").getAsString();
				refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
				br.close();
				bw.close();
		}catch(IOException e) {
			e.printStackTrace();
			}
			
			return access_Token;
		}
		
		// 유저 정보 조회
		public HashMap<String,Object> getUserInfo(String accessToken){
			HashMap<String,Object> userInfo= new HashMap<String,Object>();
			String reqURL="https://kapi.kakao.com/v2/user/me";
			try {
				URL url = new URL(reqURL);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				
				//요청 헤더에 포함되야 하는 내용
				conn.setRequestProperty("Authorization", "Bearer "  + accessToken);
				
				// 응답 내용 읽어오기
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),StandardCharsets.UTF_8));

		            String line = "";
		            String result = "";

		            while ((line = br.readLine()) != null) {
		                result += line;
		            }
		            System.out.println("responseBody:"+result);
		            JsonElement element = JsonParser.parseString(result);
		            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
		            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
		            
		            String nickName = properties.getAsJsonObject().get("nickname").getAsString();
		            String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
		            
		            userInfo.put("accessToken", accessToken);
		            userInfo.put("nickName", nickName);
		            userInfo.put("email", email);
		            
			}catch(IOException e) {
				e.printStackTrace();
			}
			return userInfo;
		}
		
		//카카오페이 준비
	    @GetMapping("/kakao/pay/{id}")
	    public String readyForKakaoPay(@PathVariable("id")Long itemId,HttpSession session){

	    	Item item = itemService.findById(itemId);

	        // 상품 결제 로직
	        //RestTemplate: REST API를 사용하여 HTTP 통신을 할 수 있게 해줌
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	        // HttpHeaders Object 생성
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", "KakaoAK 2a2a86ab386b29f21410d0a4f53b6615");
	        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	        
	        //HttpBody Object 생성
	        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
	        param.add("cid", "TC0ONETIME"); // 테스트용 가맹점 코드 (카카에 디벨로퍼에서 제공)
	        param.add("partner_order_id", item.getOrderKey()); // 값을 따로 생성해서 넣어둬야 나중에 배송관리 할 수 있을 듯
	        param.add("partner_user_id", (String)session.getAttribute("loginSession"));
	        param.add("item_name", item.getName());
	        param.add("quantity", "1"); //1로 고정
	        param.add("total_amount", String.valueOf(item.getPrice()));
	        param.add("tax_free_amount", "0");
	        param.add("approval_url", "http://localhost:8080/kakao/approval/"+itemId);
	        param.add("cancel_url", "http://localhost:8080/kakao/cancel");
	        param.add("fail_url", "http://localhost:8080/kakao/fail");
	        
	        //HttpEntitiy Object에 header와 body 넣어서 request 전송
	        HttpEntity<MultiValueMap<String, String>> kakaoPayRequest = new HttpEntity<>(param, headers);
	        
	        // exchange(요청 url, 전송 method, request, response return type)
	        ResponseEntity<String> kakaoPayResponse = restTemplate.exchange
	        		("https://kapi.kakao.com/v1/payment/ready",
	        				HttpMethod.POST,
	        				kakaoPayRequest,
	        				String.class);
	        
	        // json 방식으로 받아온 response data를 생성해둔 객체에 매핑
	        ObjectMapper mapper = new ObjectMapper();
	        KakaoPay kakaoPay = null;
	        
	        try {
	        	kakaoPay = mapper.readValue(kakaoPayResponse.getBody(), KakaoPay.class );
	        }catch(JsonProcessingException e) {
	        	e.printStackTrace();
	        }
	        String pcURL = kakaoPay.getNext_redirect_pc_url();
	        String tid = kakaoPay.getTid();
	        
	        //구매자 아이디 조회
	        String buyerEmail = (String)session.getAttribute("loginSession");
	        Long memberId= memberService.findByEmail(buyerEmail).getId();
	        
	        KakaoApprovalRequest request = new KakaoApprovalRequest();
	        request.setItemId(itemId);
	        request.setMemberId(memberId);
	        request.setTid(tid);
	        // token은 나중에 넣어주기
	        kakaoPayMapper.save(request);
	        
	        // 성공, 실패, 취소 url 설정
	        // 카카오페이 API 문서 참고
	        //String url = null;

	        return "redirect:" + pcURL;
	    }
	    
	    //카카오페이 결제 승인
	    @GetMapping("/kakao/approval/{id}")
	    public String kakaoPay(String pg_token,@PathVariable("id")Long itemId,HttpSession session,Model model) {
	    	// orderKey 가져오기
	    	Item item = itemService.findById(itemId);
	    	String orderKey= item.getOrderKey();
	    	
	    	//여기서 pgToken 넣어주기
	    	KakaoApprovalRequest request=kakaoPayMapper.findByItemId(itemId);
	    	kakaoPayMapper.updatePgToken(request.getId(), pg_token);
	    	
	    	//RestTemplate: REST API를 사용하여 HTTP 통신을 할 수 있게 해줌
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	        // HttpHeaders Object 
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", "KakaoAK 2a2a86ab386b29f21410d0a4f53b6615");
	        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	    	
	        //HttpBody Object 생성
	        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
	        param.add("cid", "TC0ONETIME"); // 테스트용 가맹점 코드 (카카에 디벨로퍼에서 제공)
	        param.add("tid", request.getTid());
	        param.add("partner_order_id", orderKey);
	        param.add("partner_user_id",(String)session.getAttribute("loginSession"));
	        param.add("pg_token", pg_token);
	        
	      //HttpEntitiy Object에 header와 body 넣어서 request 전송
	        HttpEntity<MultiValueMap<String, String>> kakaoPayRequest = new HttpEntity<>(param, headers);
	        
	        // exchange(요청 url, 전송 method, request, response return type)
	        ResponseEntity<String> kakaoPayResponse = restTemplate.exchange
	        		("https://kapi.kakao.com/v1/payment/approve",
	        				HttpMethod.POST,
	        				kakaoPayRequest,
	        				String.class);
	        
	        // json 방식으로 받아온 response data를 생성해둔 객체에 매핑
	        ObjectMapper mapper = new ObjectMapper();
	        ApprovalResponse response = null;
	        
	        try {
	        	response = mapper.readValue(kakaoPayResponse.getBody(), ApprovalResponse.class );
	        }catch(JsonProcessingException e) {
	        	e.printStackTrace();
	        }
	        
	        // 결제 완료 시 상태 변경
	    	Shipping shipping=shippingMapper.findByItemId(item.getId());
	    	shipping.setShippingStatus(Status.READY);
	    	// 구매자 아이디 넣어주기
	    	Member buyer = memberService.findByEmail(response.getPartner_user_id());
	    	shipping.setMemberId(buyer.getId());
	    	
	    	model.addAttribute("memberId", buyer.getId());
	    	System.out.println("responseBody:"+kakaoPayResponse.getBody());
	    	
	    	return "order/success";
	    }
}
