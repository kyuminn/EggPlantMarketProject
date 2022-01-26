package teamB.market.domain.question.repository;

import teamB.market.domain.question.Reply;

import java.util.List;

public interface ReplyRepository {
    Reply save(Reply reply);
    Reply findById(long id);
    List<Reply> findByQuestionId(long id);
    void delete(long id);
}
