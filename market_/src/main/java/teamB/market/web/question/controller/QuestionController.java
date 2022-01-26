package teamB.market.web.question.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import teamB.market.domain.item.Item;
import teamB.market.domain.member.Member;
import teamB.market.domain.question.IsReplied;
import teamB.market.domain.question.Question;
import teamB.market.domain.question.Reply;
import teamB.market.web.item.service.ItemService;
import teamB.market.web.member.service.MemberService;
import teamB.market.web.question.form.AddQuestionForm;
import teamB.market.web.question.form.EditQuestionForm;
import teamB.market.web.question.form.QuestionList;
import teamB.market.web.question.service.QuestionService;
import teamB.market.web.reply.service.ReplyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionService questionService;
    private final ItemService itemService;
    private final MemberService memberService;
    private final ReplyService replyService;

    @GetMapping("add/{itemId}")
    public String addForm(@PathVariable long itemId, Model model, HttpSession httpSession){

        // 현재시간 저장
        AddQuestionForm form = new AddQuestionForm();
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        String currentTime = current.format(formatter);

        // 회원 닉네임 저장
//        Member loginMember = (Member) httpSession.getAttribute("loginMember");
        String loginEmail = (String) httpSession.getAttribute("loginSession");
        Member loginMember = memberService.findByEmail(loginEmail);
        String nickname = loginMember.getNickname();

        form.setItemId(itemId);
        form.setWriter(nickname);
        form.setDate(currentTime);

        model.addAttribute("question", form);

        return "question/addForm";
    }

    @PostMapping("/add/{itemId}")
    public String add(@Validated @ModelAttribute("question") AddQuestionForm form,
                     BindingResult bindingResult,  @PathVariable long itemId, HttpSession httpSession){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "question/addForm";
        }

        // 세션에 저장된 회원정보 가져오기
        String loginEmail = (String) httpSession.getAttribute("loginSession");
        Member loginMember = memberService.findByEmail(loginEmail);

        // 성공 로직
        Question question = Question.builder()
                .isReplied(IsReplied.WAIT)
                .content(form.getContent())
                .currentTime(form.getDate())
                .memberId(loginMember.getId())
                .writer(loginMember.getNickname())
                .title(form.getTitle())
                .itemId(itemId)
                .build();

        questionService.save(question);

        return "redirect:/question/list/" + itemId;
    }

    @GetMapping("/list/{itemId}")
    public String list(@PathVariable long itemId, Model model){

        List<Question> questionList = questionService.findByItemId(itemId);

        model.addAttribute("questionList", questionList);
        model.addAttribute("itemId", itemId);
        
        // 판매자에게는 문의하기 버튼 비활성화 하기
        // 판매자 이메일과 세션에 저장된 값을 비교 위해 이메일 model에 넣어줌
        Long memberId = itemService.findById(itemId).getMemberId();
        model.addAttribute("sellerEmail", memberService.findById(memberId).getEmail());

        return "question/list";
    }

    @GetMapping("/myList/{memberId}")
    public String myList(@PathVariable long memberId, Model model){

        List<Question> questionList = questionService.findByMemberId(memberId);

        model.addAttribute("questionList", questionList);

        return "question/myList";
    }

    @GetMapping("/detail/{questionId}")
    public String detail(@PathVariable long questionId,
                         @RequestParam(required = false) String content, Model model, HttpServletRequest request){

        HttpSession session = request.getSession(false);
//        Member loginMember = (Member) session.getAttribute("loginMember");
        String loginEmail = (String) session.getAttribute("loginSession");
        Member loginMember = memberService.findByEmail(loginEmail);
        Question question = questionService.findById(questionId);

        // question 작성한 회원정보 가져오기
//        Member buyer = memberService.findById(question.getMemberId());

        // question 객체의 아이템 아이디로 item 객체 가져오기
        Item item = itemService.findById(question.getItemId());

        // 아이템 판매자 회원정보 가져오기
        Member seller = memberService.findById(item.getMemberId());

        // 세션에 저장된 회원정보와 상품 등록 회원정보 비교
        if(loginMember.getEmail().equals(seller.getEmail())){
            model.addAttribute("status", true);
            model.addAttribute("reply_state", 1);
        }

        // 아이템 판매자와 질문작성자만 댓글 등록 가능
//        if(loginMember.getEmail().equals(seller.getEmail()) || loginMember.getEmail().equals(buyer.getEmail())){
//            model.addAttribute("reply_status", true);
//        }

        // --------------------------------------------------------- //

        List<Reply> replyList = replyService.findByQuestionId(questionId);

        model.addAttribute("question", question);
        model.addAttribute("replyList", replyList);
        model.addAttribute("member", loginMember);

        return "question/detail";
    }

    @GetMapping("/delete/{questionId}")
    public String delete(@PathVariable long questionId, Model model){
        // question 객체에서 itemId 가져오기
        Question question = questionService.findById(questionId);
        questionService.delete(questionId);
//        Question question = questionService.findById(questionId);

        model.addAttribute("itemId", question.getItemId());

        return "question/deleteSuccess";
    }

    @GetMapping("/edit/{questionId}")
    public String editForm(@PathVariable long questionId, Model model){
        Question question = questionService.findById(questionId);
        model.addAttribute("question", question);
        return "question/editForm";
    }

    @PostMapping("edit/{questionId}")
    public String edit(@Validated @ModelAttribute("question")EditQuestionForm form, BindingResult bindingResult, @PathVariable long questionId){
        Question question = questionService.findById(questionId);
        question.setTitle(form.getTitle());
        question.setContent(form.getContent());
        questionService.update(questionId, question);
        return "redirect:/question/detail/" + questionId;
    }

}
