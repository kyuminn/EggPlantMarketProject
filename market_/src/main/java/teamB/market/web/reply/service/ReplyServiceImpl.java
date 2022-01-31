package teamB.market.web.reply.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import teamB.market.domain.question.Reply;
import teamB.market.domain.question.mapper.ReplyMapper;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    //private final replyMapper replyMapper;
	private final ReplyMapper replyMapper;

    @Override
    public void save(Reply reply) {
        replyMapper.save(reply);
    }

    @Override
    public List<Reply> findByQuestionId(long id) {
        return replyMapper.findByQuestionId(id);
    }

    @Override
    public void delete(long id) {
        replyMapper.delete(id);
    }

    @Override
    public Reply findById(long id) {
        return replyMapper.findById(id);
    }
}
