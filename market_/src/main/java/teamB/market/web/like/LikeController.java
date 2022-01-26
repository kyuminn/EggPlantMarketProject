package teamB.market.web.like;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import teamB.market.domain.item.Item;
import teamB.market.domain.like.Like;
import teamB.market.web.item.service.ItemService;
import teamB.market.web.like.service.LikeService;
import teamB.market.web.member.service.MemberService;

@Controller
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
	
	private final MemberService memberService;
	private final LikeService likeService;
	private final ItemService itemService;
	
	
    // 찜 목록 추가
    @ResponseBody
    @RequestMapping(method=RequestMethod.POST)
    public int addLike(@RequestParam("loginSession")String email,@RequestParam("itemId")long itemId) {
    	int result = -1;
    	
        Long memberId = memberService.findByEmail(email).getId();
        Like like = new Like();
        like.setItemId(itemId);
        like.setMemberId(memberId);
        result=likeService.saveLike(like);
    	return result;
    }

	@GetMapping("/list")
	public String likeList(HttpSession session,Model model) {
		String loginSession=(String)session.getAttribute("loginSession");
		long memberId = memberService.findByEmail(loginSession).getId();
		List<Like> myLikes = likeService.selectMyLikeList(memberId);
		List<Item> myLikesItems = new ArrayList<>();
		
		for(int i=0; i<myLikes.size(); i++) {
			long itemId=myLikes.get(i).getItemId();
			Item item = itemService.findById(itemId);
			myLikesItems.add(item);
		}
		model.addAttribute("loginMember", memberService.findByEmail(loginSession));
		model.addAttribute("myLikesItems", myLikesItems);
		return "like/myList";
	}
	
	@GetMapping("/remove/{itemId}")
	public String delete(@PathVariable("itemId")long itemId) {
		likeService.remove(itemId);
		// redirect 시 controller로 간다는거 잊지말구..
		return "redirect:/like/list";
	}
}
