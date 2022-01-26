package teamB.market.domain.member.repository;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import teamB.market.domain.member.Member;

@Mapper
public interface  MemberRepository {
	
	@Insert("insert into member("
			+ "id,email,name,pwd,nickname,addr,route,phoneNum,emailAuthCode,isEmailAuth) "
			+ "values(member_seq.nextval,#{email},#{name},#{pwd},#{nickname},#{addr},#{route},#{phoneNum},#{emailAuthCode},#{isEmailAuth})")
    void save(Member member);
	
    Member findById(long id);
    
    @Select("select * from member where email=#{email,jdbcType=VARCHAR}")
    Member findByEmail(String email);
    @Select("select * from member where phoneNum=#{phoneNum}")
    Member findByPhoneNum(String phoneNum);
    
    List<Member> findAll();
    void update(long id, Member updateParam);
    void delete(long id);

}
