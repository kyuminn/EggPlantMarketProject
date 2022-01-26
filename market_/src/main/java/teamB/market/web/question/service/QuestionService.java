package teamB.market.web.question.service;

import teamB.market.domain.question.Question;

import java.util.List;

public interface QuestionService {
    Question save(Question question);
    List<Question> list();
    Question findById(long id);
    List<Question> findByItemId(long itemId);
    void delete(long id);
    void update(long id, Question updateParam);
    List<Question> findByMemberId(long id);
}
