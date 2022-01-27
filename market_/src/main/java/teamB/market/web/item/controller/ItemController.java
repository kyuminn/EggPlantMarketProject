package teamB.market.web.item.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import teamB.market.domain.item.Item;
import teamB.market.domain.member.Member;
import teamB.market.domain.member.Role;
import teamB.market.domain.rate.Rate;
import teamB.market.domain.rate.mapper.RateMapper;
import teamB.market.domain.shipping.Shipping;
import teamB.market.domain.shipping.Status;
import teamB.market.domain.shipping.mapper.ShippingMapper;
import teamB.market.web.item.form.AddItemForm;
import teamB.market.web.item.form.EditItemForm;
import teamB.market.web.item.service.ItemService;
import teamB.market.web.item.service.S3Service;
import teamB.market.web.member.service.MemberService;


@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {


	private final ItemService itemService;
	private final MemberService memberService;
	private final S3Service s3Service;
	private final ShippingMapper shippingMapper;
	private final RateMapper rateMapper;
	
    @GetMapping("/list")
    public String itemList(Model model){
        // 상품 리스트 가져오기


        // 카테고리 별로 조회수가 높은 상품 조회 후 model 에 담기

        return "item/list";
    }
    
    // 나의 판매글 목록 가져오기
    @GetMapping("/myList/{memberId}")
    public String myItemList(@PathVariable("memberId")long memberId,Model model) {
    	List<Item> ls = itemService.findMyItemList(memberId);
    	
    	// 구매되지 않음
    		List<Item> onSaleItemList = new ArrayList<Item>();
    	// 결제 완료
    		List<Item> readyItemList = new ArrayList<Item>();
    	// 배송 중
    		List<Item> shippingItemList = new ArrayList<Item>();
    	// 배송 완료
    		List<Item> completeItemList = new ArrayList<Item>();
    		
    	for (int i=0; i<ls.size();i++) {
    		Item item = ls.get(i);
    		Status shippingStatus = shippingMapper.findByItemId(item.getId()).getShippingStatus();
    		if (shippingStatus.equals(Status.ONSALE)) {
    			onSaleItemList.add(item);
    		}else if(shippingStatus.equals(Status.READY)) {
    			readyItemList.add(item);
    		}else if(shippingStatus.equals(Status.SHIPPING)) {
    			shippingItemList.add(item);
    		}else if(shippingStatus.equals(Status.COMPLETE)) {
    			completeItemList.add(item);
    		}
    	}
    	model.addAttribute("onSaleItemList",onSaleItemList );
    	model.addAttribute("readyItemList", readyItemList);
    	model.addAttribute("shippingItemList",shippingItemList );
    	model.addAttribute("completeItemList", completeItemList);
    	
    	// 평점 보여주기 위한 Rate hashMap <itemId, rating>
    	HashMap<Long,Float> map = new HashMap<>();
    	for(Item item : completeItemList) {
    		Rate rate =rateMapper.findByItemId(item.getId());
    		map.put(item.getId(), rate.getRating());
    	}
    	model.addAttribute("map", map);
    	return "item/myList";
    }

    @GetMapping("/detail/{id}")
    public String itemDetail(@PathVariable long id, Model model){
    	
    	// 조회수 +1
    	itemService.updateHit(id);
    	
        // 아이디로 상품 정보 가져온 후 model 에 담기
    	Item item = itemService.findById(id);
    	model.addAttribute("item", item);
    	
    	// 판매자 닉네임 가져오기
    	Member member = memberService.findById(item.getMemberId());

    	model.addAttribute("member", member);
        return "item/detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id,Model model){
    	Item item =itemService.findById(id);
    	Long memberId = item.getId();
    	// s3에 업로드 된 썸네일 삭제
    	s3Service.delete(item.getFilePath());
        // 상품 정보 삭제
    	itemService.delete(id);
    	model.addAttribute("memberId", memberId);
        return "item/deleteSuccess";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable long id, Model model){

        // 아이디로 상품 정보 가져온 후 model 에 담기
    	Item item = itemService.findById(id);
    	EditItemForm form = new EditItemForm();

    	form.setCategory(item.getCategory());
    	form.setContent(item.getContent());
    	form.setPrice(item.getPrice());
    	form.setName(item.getName());
    	
    	model.addAttribute("item",form);
    	model.addAttribute("storedThumbnailPath", item.getFilePath());
    	model.addAttribute("itemId", id);
        return "item/editForm";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Validated @ModelAttribute("item")EditItemForm form, BindingResult bindingResult
    		,@PathVariable("id")long itemId,@RequestParam("uploadFile")MultipartFile thumbnailImage){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "item/editForm";
        }
        
        Item item = itemService.findById(itemId);
        item.setCategory(form.getCategory());
        item.setContent(form.getContent());
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        
        // 기존 업로드 된 썸네일 삭제
        s3Service.delete(itemService.findById(itemId).getFilePath());
        
        //파일 aws s3 에 수정된 썸네일 업로드
        String storedFilePath = null;
        try {
        	storedFilePath= s3Service.upload(thumbnailImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        item.setFilePath(storedFilePath);
        

        
        // 성공 로직
        itemService.updateItemDetail(itemId, item);
        long memberId = itemService.findById(itemId).getMemberId();
        
        return "redirect:/item/myList/"+memberId;
    }


    @GetMapping("/add")
    public String addForm(@ModelAttribute("itemAddFormData")AddItemForm form,Model model,HttpSession session){
    	// 로그인 되어 있지 않을 경우 인터셉터로 리다이렉트 하는 방법 구현해놓기..
    	
    	// 세션에서 로그인 정보 가져와서 모델에 이메일 값 넣어주기
    	model.addAttribute("loginSession",session.getAttribute("loginSession"));
        return "item/addForm";
    }

    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("itemAddFormData") AddItemForm form, BindingResult bindingResult,
    		@RequestParam("loginSession")String email,@RequestParam("uploadFile")MultipartFile thumbnailImage,Model model){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "item/addForm";
        }
        
        Long memberId= memberService.findByEmail(email).getId();
        
     // Member 객체 Role 수정
        if(memberService.findById(memberId).getRole().equals(Role.BUYER)) {
            memberService.updateRole(memberId);
        }
        
        
        // 아이템 객체 추가
        Item item = new Item();
        item.setCategory(form.getCategory());
        item.setContent(form.getContent());
        item.setHit(0);
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        item.setMemberId(memberId);
        
        // 아이템 등록 시 주문번호 생성해서 넣어줌
        String orderId = form.getCategory()+RandomStringUtils.randomAlphanumeric(10);
        item.setOrderKey(orderId);

        //파일 aws s3 에 업로드
        String storedFilePath = null;
        try {
        	storedFilePath= s3Service.upload(thumbnailImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        // 메인에서 아이템 객체의 썸네일 가져오기 위해 item 객체에 썸네일 경로 저장
        item.setFilePath(storedFilePath);
        
        //저장
        itemService.save(item);

        // 아이템 추가 시 shipping 객체에 배송상태 저장
        Shipping shipping = new Shipping();
        shipping.setShippingStatus(Status.ONSALE);

        // 구매자 아이디(memberId)는 결제완료시 넣어주기
        shipping.setItemId(itemService.findByOrderKey(orderId).getId());
        shippingMapper.save(shipping);
        
        return "redirect:/item/myList/"+memberId;
    }


}
