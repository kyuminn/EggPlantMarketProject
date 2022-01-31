package teamB.market.domain.question.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import teamB.market.domain.question.Reply;

@Mapper
public interface ReplyMapper {
	@Insert("insert into reply(id,questionId,memberId,content) values(reply_seq.nextval,#{questionId},#{memberId},#{content})")
    void save(Reply reply);
	
	@Select("select * from reply where id=#{id}")
    Reply findById(long id);
	
	@Select("select * from reply where questionId=#{questionId}")
    List<Reply> findByQuestionId(long questionId);
	
	@Delete("delete from reply where id=#{id}")
	void delete(long id);
}
