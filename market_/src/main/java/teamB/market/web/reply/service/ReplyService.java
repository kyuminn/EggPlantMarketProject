package teamB.market.web.reply.service;

import teamB.market.domain.question.Reply;

import java.util.List;

public interface ReplyService {
    void save(Reply reply);
    List<Reply> findByQuestionId(long id);
    void delete(long id);
    Reply findById(long id);
}
