package teamB.market.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import teamB.market.domain.item.Item;
import teamB.market.domain.member.Member;
import teamB.market.web.item.service.ItemService;
import teamB.market.web.member.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final ItemService itemService;
	private final MemberService memberService;
	
	@GetMapping("/")
	public String main(Model model,HttpServletRequest request) {
		List<Item> items = itemService.selectMainItemList();
		model.addAttribute("items", items);
		
		
		HttpSession session = request.getSession(false);
		Member loginMember = null;
		if(session!=null) {
			loginMember = memberService.findByEmail((String)session.getAttribute("loginSession"));
		}

		model.addAttribute("loginMember", loginMember);
		return "main";
	}
}
