package teamB.market.domain.question.repository;

import teamB.market.domain.question.Question;

import java.util.List;

public interface QuestionRepository {
    Question save(Question question);
    Question findById(long id);
    List<Question> findAll();
    List<Question> findByItemId(long itemId);
    List<Question> findByMemberId(long id);
    void update(long id, Question updateParam);
    void delete(long id);
}
