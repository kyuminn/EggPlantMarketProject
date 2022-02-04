package teamB.market.web.question.service;

import java.util.List;

import teamB.market.domain.question.IsReplied;
import teamB.market.domain.question.Question;

public interface QuestionService {
	
	// 저장
    void save(Question question);
    
    // 질문 시퀀스로 찾기
    Question findById(long id);
    
    // 특정 아이템에 대한 질문 리스트 가져오기
    List<Question> findByItemId(long itemId);
    
    // 질문 삭제 
    void delete(long id);
    
    // 질문 내용 수정
    void update(long id, Question updateParam);
    
    // 특정 회원이 한 질문 리스트 가져오기
    List<Question> findByMemberId(long id);
    
    // 답변 상태 업데이트
    void updateReplyStatus(long id,IsReplied isReplied);
}
