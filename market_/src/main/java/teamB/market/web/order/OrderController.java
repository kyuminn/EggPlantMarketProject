package teamB.market.web.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import teamB.market.domain.item.Item;
import teamB.market.domain.member.Member;
import teamB.market.domain.rate.Rate;
import teamB.market.domain.rate.repository.RateRepository;
import teamB.market.domain.shipping.Shipping;
import teamB.market.domain.shipping.Status;
import teamB.market.domain.shipping.repository.ShippingRepository;
import teamB.market.web.item.service.ItemService;
import teamB.market.web.member.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
	
	private final ItemService itemService;
	private final ShippingRepository shippingRepository;
	private final MemberService memberService;
	private final RateRepository rateRepository;
	
	@RequestMapping("/myList/{memberId}")
	public String myOrderList(@PathVariable("memberId")Long memberId,Model model) {
		
		List<Shipping> ls = shippingRepository.findByMemberId(memberId);
		
		//List<Item> itemLs= new ArrayList<Item>();
		List<Item> readyItem = new ArrayList<Item>();
		List<Item> shippingItem = new ArrayList<Item>();
		List<Item> completeItem = new ArrayList<Item>();
		
		for(int i=0; i<ls.size(); i++) {
			Item item = itemService.findById(ls.get(i).getItemId());
			if(item!=null) {
				Status status = shippingRepository.findByItemId(item.getId()).getShippingStatus();
				if(status.equals(Status.READY)) {
					readyItem.add(item);
				}else if(status.equals(Status.SHIPPING)) {
					shippingItem.add(item);
				}else if(status.equals(Status.COMPLETE)) {
					completeItem.add(item);
				}
			}
		}
		
		
		model.addAttribute("readyItem", readyItem);
		model.addAttribute("shippingItem", shippingItem);
		model.addAttribute("completeItem", completeItem);
		
		Member loginMember = memberService.findById(memberId);
		model.addAttribute("loginMember", loginMember);
		
		return "order/myList";
	}
	
	@GetMapping("/release/{id}")
	public String releaseItem(@PathVariable("id")Long itemId) {
		// 출고 완료된 아이템 배송 상태 바꿔주기
		Shipping shipping = shippingRepository.findByItemId(itemId);
		shipping.setShippingStatus(Status.SHIPPING);
		Long memberId = itemService.findById(itemId).getMemberId();
		return "redirect:/order/myList/"+memberId;
	}
	
	@GetMapping("/confirm/{id}")
	public String confirmOrder(@PathVariable("id")Long itemId, Model model) {
		// 구매 확정 
		Shipping shipping = shippingRepository.findByItemId(itemId);
		shipping.setShippingStatus(Status.COMPLETE);
		
		// 구매 확정되면 판매자 객체 포인트 업데이트
		Item item = itemService.findById(itemId);
		Member seller = memberService.findById(item.getMemberId());
		seller.setPoint(item.getPrice());
		
		model.addAttribute("item", itemService.findById(itemId));
		
		// 구매 확정 후 별점 페이지로 이동
		return "order/rating";
	}
	
	@PostMapping("/rating/{id}")
	public String giveRating(@PathVariable("id")Long itemId,@RequestParam("buyerEmail")String buyerEmail
			,@RequestParam("rating")String rating) {
	  // 구매자 정보
		long memberId=memberService.findByEmail(buyerEmail).getId();
	  // rate 객체 저장
		Rate rate = new Rate();
		rate.setItemId(itemId);
		rate.setMemberId(memberId);
		rate.setRating(Float.valueOf(rating));
		System.out.println(rate);
		rateRepository.save(rate);
		return "redirect:/order/myList/"+memberId;
	}
}
