package teamB.market.web.reply.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teamB.market.domain.member.Member;
import teamB.market.domain.question.IsReplied;
import teamB.market.domain.question.Question;
import teamB.market.domain.question.Reply;
import teamB.market.web.member.service.MemberService;
import teamB.market.web.question.service.QuestionService;
import teamB.market.web.reply.service.ReplyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("reply")
public class ReplyController {

    private final ReplyService replyService;
    private final MemberService memberService;
    private final QuestionService questionService;

    @PostMapping("/{questionId}")
    public String reply(@RequestParam String content, @PathVariable long questionId, HttpServletRequest request, Model model){

        HttpSession session = request.getSession(false);
        String loginEmail = (String) session.getAttribute("loginSession");
        Member loginMember = memberService.findByEmail(loginEmail);
        Question question = questionService.findById(questionId);

        // 답글 저장
        Reply reply = Reply.builder()
                .questionId(questionId)
                .content(content)
                .memberId(loginMember.getId())
                .build();

        replyService.save(reply);

        // 문의사항 답변 완료
        questionService.updateReplyStatus(questionId,IsReplied.COMPLETE);

        // 세션에 저장된 회원정보와 답글 회원정보 비교
        if(reply.getMemberId() == loginMember.getId()){
            model.addAttribute("status", true);
        }

        return "redirect:/question/detail/" + questionId;
    }

    @GetMapping("/{replyId}")
    public String delete(@PathVariable long replyId, Model model){
        Reply reply = replyService.findById(replyId);
        model.addAttribute("questionId", reply.getQuestionId());
        Question question = questionService.findById(reply.getQuestionId());

        // 댓글 삭제 후 댓글 리스트 조회
        replyService.delete(replyId);

        List<Reply> replyList = replyService.findByQuestionId(reply.getQuestionId());

        // 해당 문의사항에 답변 리스트가 없는 경우 답변상태를 WAIT로 수정
        if(replyList.isEmpty()){
        	questionService.updateReplyStatus(question.getId(), IsReplied.WAIT);
        }
        return "reply/deleteSuccess";
    }
}
