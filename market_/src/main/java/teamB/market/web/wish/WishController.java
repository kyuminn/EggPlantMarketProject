package teamB.market.web.wish;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import teamB.market.domain.item.Item;
import teamB.market.domain.wish.Wish;
import teamB.market.web.item.service.ItemService;
import teamB.market.web.member.service.MemberService;
import teamB.market.web.wish.service.WishService;

@Controller
@RequestMapping("/wish")
@RequiredArgsConstructor
public class WishController {
	
	private final MemberService memberService;
	private final WishService wishService;
	private final ItemService itemService;
	
	
    // 찜 목록 추가
    @ResponseBody
    @RequestMapping(method=RequestMethod.POST)
    public int addWish(@RequestParam("loginSession")String email,@RequestParam("itemId")long itemId) {
    	int result = -1;
    	
        Long memberId = memberService.findByEmail(email).getId();
        Wish wish = new Wish();
        wish.setItemId(itemId);
        wish.setMemberId(memberId);
        result=wishService.saveWish(wish);
    	return result;
    }

	@GetMapping("/list")
	public String wishList(HttpSession session,Model model) {
		String loginSession=(String)session.getAttribute("loginSession");
		long memberId = memberService.findByEmail(loginSession).getId();
		List<Wish> myWishes = wishService.selectMyWishList(memberId);
		List<Item> myWishItems = new ArrayList<>();
		
		for(int i=0; i<myWishes.size(); i++) {
			long itemId=myWishes.get(i).getItemId();
			Item item = itemService.findById(itemId);
			myWishItems.add(item);
		}
		model.addAttribute("loginMember", memberService.findByEmail(loginSession));
		model.addAttribute("myWishItems", myWishItems);
		return "wish/myList";
	}
	
	@GetMapping("/remove/{itemId}")
	public String delete(@PathVariable("itemId")long itemId) {
		wishService.remove(itemId);
		// redirect 시 controller로 간다는거 잊지말구..
		return "redirect:/wish/list";
	}
}
