package teamB.market.web.question.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamB.market.domain.question.Question;
import teamB.market.domain.question.repository.QuestionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> list() {
        return questionRepository.findAll();
    }

    @Override
    public Question findById(long id) {
        return questionRepository.findById(id);
    }

    @Override
    public List<Question> findByItemId(long itemId) {
        return questionRepository.findByItemId(itemId);
    }

    @Override
    public void delete(long id) {
        questionRepository.delete(id);
    }

    @Override
    public void update(long id, Question updateParam) {
        questionRepository.update(id, updateParam);
    }

    @Override
    public List<Question> findByMemberId(long id) {
        return questionRepository.findByMemberId(id);
    }
}
