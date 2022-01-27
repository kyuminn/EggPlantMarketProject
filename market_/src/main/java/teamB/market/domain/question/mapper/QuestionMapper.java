package teamB.market.domain.question.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import teamB.market.domain.question.Question;

@Mapper
public interface QuestionMapper {
	
	
    void save(Question question);
    Question findById(long id);
    List<Question> findAll();
    List<Question> findByItemId(long itemId);
    List<Question> findByMemberId(long id);
    void update(long id, Question updateParam);
    void delete(long id);
}
