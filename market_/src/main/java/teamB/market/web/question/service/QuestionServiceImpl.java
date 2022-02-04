package teamB.market.web.question.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import teamB.market.domain.question.IsReplied;
import teamB.market.domain.question.Question;
import teamB.market.domain.question.mapper.QuestionMapper;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

	private final QuestionMapper questionMapper;

    @Override
    public void save(Question question) {
         questionMapper.save(question);
    }

    @Override
    public Question findById(long id) {
        return questionMapper.findById(id);
    }

    @Override
    public List<Question> findByItemId(long itemId) {
        return questionMapper.findByItemId(itemId);
    }

    @Override
    public void delete(long id) {
        questionMapper.delete(id);
    }

    @Override
    public void update(long id, Question updateParam) {
        questionMapper.update(id, updateParam);
    }

    @Override
    public List<Question> findByMemberId(long id) {
        return questionMapper.findByMemberId(id);
    }

	@Override
	public void updateReplyStatus(long id,IsReplied isReplied) {
		questionMapper.updateReplyStatus(id,isReplied);
		
	}
}
