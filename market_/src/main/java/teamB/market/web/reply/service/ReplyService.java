package teamB.market.web.reply.service;

import teamB.market.domain.question.Reply;

import java.util.List;

public interface ReplyService {
	
	// 답글 저장
    void save(Reply reply);
    
    // 특정 질문에 대한 답글 리스트 가져오기
    List<Reply> findByQuestionId(long id);
    
    // 답글 삭제
    void delete(long id);
    
    // 답글 시퀀스로 검색
    Reply findById(long id);
}
