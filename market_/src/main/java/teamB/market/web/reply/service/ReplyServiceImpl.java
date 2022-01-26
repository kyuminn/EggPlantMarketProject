package teamB.market.web.reply.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamB.market.domain.question.Reply;
import teamB.market.domain.question.repository.ReplyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public Reply save(Reply reply) {
        return replyRepository.save(reply);
    }

    @Override
    public List<Reply> findByQuestionId(long id) {
        return replyRepository.findByQuestionId(id);
    }

    @Override
    public void delete(long id) {
        replyRepository.delete(id);
    }

    @Override
    public Reply findById(long id) {
        return replyRepository.findById(id);
    }
}
