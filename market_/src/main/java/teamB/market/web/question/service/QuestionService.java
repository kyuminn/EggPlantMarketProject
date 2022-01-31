package teamB.market.web.question.service;

import java.util.List;

import teamB.market.domain.question.IsReplied;
import teamB.market.domain.question.Question;

public interface QuestionService {
    void save(Question question);
    List<Question> list();
    Question findById(long id);
    List<Question> findByItemId(long itemId);
    void delete(long id);
    void update(long id, Question updateParam);
    List<Question> findByMemberId(long id);
    void updateReplyStatus(long id,IsReplied isReplied);
}
