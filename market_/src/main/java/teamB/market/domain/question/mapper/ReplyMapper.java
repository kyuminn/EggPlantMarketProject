package teamB.market.domain.question.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import teamB.market.domain.question.Reply;

@Mapper
public interface ReplyMapper {
    void save(Reply reply);
    Reply findById(long id);
    List<Reply> findByQuestionId(long id);
    void delete(long id);
}
