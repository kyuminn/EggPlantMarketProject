package teamB.market.domain.question.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import teamB.market.domain.question.IsReplied;
import teamB.market.domain.question.Question;

@Mapper
public interface QuestionMapper {
	
	@Insert("insert into question(id,itemId,memberId,title,writer,content,currentTime,isReplied) "
			+ "values(question_seq.nextval,#{itemId},#{memberId},#{title},#{writer},#{content},#{currentTime},#{isReplied})")
    void save(Question question);
	
	@Select("select * from question where id=#{id}")
    Question findById(long id);
    
	@Select("select * from question")
	List<Question> findAll();
    
	@Select("select * from question where itemId=#{itemId}")
	List<Question> findByItemId(long itemId);
    
	@Select("select * from question where memberId=#{memberId}")
	List<Question> findByMemberId(long memberId);
    
	@Update("update question set title=#{question.title},content=#{question.content},currentTime=#{question.currentTime}")
	void update(@Param("id")long id, @Param("question")Question updateParam);
    
	@Update("update question set isReplied=#{isReplied} where id=#{id}")
	void updateReplyStatus(@Param("id")long id,@Param("isReplied")IsReplied isReplied);
	
	@Delete("delete question where id=#{id}")
	void delete(long id);
}
