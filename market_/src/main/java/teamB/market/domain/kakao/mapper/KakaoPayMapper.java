package teamB.market.domain.kakao.mapper;

import org.apache.ibatis.annotations.*;

import teamB.market.domain.kakao.KakaoApprovalRequest;

@Mapper
public interface KakaoPayMapper {
	
	@Insert("insert into kakaopay(id,itemId,memberId,tid) values(kakaopay_seq.nextval,#{itemId},#{memberId},#{tid})")
	public void save(KakaoApprovalRequest request);
	
	@Select("select * from kakaopay where itemId=#{itemId}")
	public KakaoApprovalRequest findByItemId(long itemId);
	
	@Update("update kakaopay set pgToken=#{pgToken} where id=#{id}")
	public void updatePgToken(@Param("id")long id,@Param("pgToken")String pgToken);
}
