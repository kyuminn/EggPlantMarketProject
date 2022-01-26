package teamB.market;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import teamB.market.domain.item.Item;
import teamB.market.domain.item.repository.ItemRepository;
import teamB.market.domain.member.Address;
import teamB.market.domain.member.EmailAuth;
import teamB.market.domain.member.Member;
import teamB.market.domain.member.repository.MemberRepository;
import teamB.market.domain.question.IsReplied;
import teamB.market.domain.question.Question;
import teamB.market.domain.question.repository.QuestionRepository;
import teamB.market.domain.shipping.Shipping;
import teamB.market.domain.shipping.Status;
import teamB.market.domain.shipping.repository.ShippingRepository;

@Component
@RequiredArgsConstructor
public class TestDataInit {
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	private final QuestionRepository questionRepository;
	private final ShippingRepository shippingRepository;
	
	@PostConstruct
	public void init() {
//		Address addr = new Address();
//		addr.setPostCode("1234");
//		addr.setRoadAddr("신반포로 1");
//		addr.setDetailAddr("test");
//		Member member = Member.builder()
//				.email("test@gmail.com")
//				.name("박규민")
//				.phoneNum("01011111111")
//				.pwd("123!")
//				.isEmailAuth(EmailAuth.COMPLETE)
//				.addr("1234 신반포로 1 상세주소")
//				.nickname("테스트닉네임1")
//				.build();
//		
//		memberRepository.save(member);
//		
//		
//		Member member2 = Member.builder()
//				.email("test1@gmail.com")
//				.name("박규원")
//				.phoneNum("01022222222")
//				.pwd("123!")
//				.isEmailAuth(EmailAuth.COMPLETE)
//				.addr("1234 신반포로 1 상세주소")
//				.nickname("테스트닉네임2")
//				.build();
//		memberRepository.save(member2);
//		
//		Item item = Item.builder()
//				.content("content")
//				.name("test")
//				.price(10000)
//				.orderKey("testorderid")
//				.memberId(1)
//				.filePath("https://images.unsplash.com/photo-1642764733197-b560fca58f41?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80")
//				.build();
//				
//		itemRepository.save(item);
//		
//        Question question = Question.builder()
//                .itemId(1)
//                .title("샘플 데이터")
//                .currentTime("2022년 01월 19일")
//				.writer("샘플 작성자")
//                .memberId(1)
//                .content("샘플 내용")
//                .isReplied(IsReplied.WAIT)
//                .build();
//
//        questionRepository.save(question);
//        
//        
//        Shipping shipping = Shipping.builder()
//        		.itemId(1)
//        		.memberId(1)
//        		.shippingStatus(Status.ONSALE)
//        		.build();
//        
//        shippingRepository.save(shipping);
	}
}
