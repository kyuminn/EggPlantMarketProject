package teamB.market.domain.question.mapper;

import java.util.List;

import teamB.market.domain.question.Reply;

public interface ReplyMapper {
    void save(Reply reply);
    Reply findById(long id);
    List<Reply> findByQuestionId(long id);
    void delete(long id);
}
