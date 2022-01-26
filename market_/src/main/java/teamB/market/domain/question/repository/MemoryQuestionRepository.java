package teamB.market.domain.question.repository;

import org.springframework.stereotype.Repository;
import teamB.market.domain.member.Member;
import teamB.market.domain.question.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MemoryQuestionRepository implements QuestionRepository {

    private static Map<Long, Question> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Question save(Question question) {
        question.setId(++sequence);
        store.put(question.getId(), question);
        return question;
    }

    @Override
    public Question findById(long id) {
        return store.get(id);
    }

    @Override
    public List<Question> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Question> findByItemId(long itemId) {
        return findAll().stream()
                .filter(q -> q.getItemId() == itemId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Question> findByMemberId(long memberId) {
        return findAll().stream()
                .filter(q -> q.getMemberId() == memberId)
                .collect(Collectors.toList());
    }

    @Override
    public void update(long id, Question updateParam) {
        Question question = findById(id);
        question.setTitle(updateParam.getTitle());
        question.setContent(updateParam.getContent());
    }

    @Override
    public void delete(long id) {
        store.remove(id);
    }
}
