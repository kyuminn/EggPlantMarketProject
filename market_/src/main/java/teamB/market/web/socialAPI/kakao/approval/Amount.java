package teamB.market.web.socialAPI.kakao.approval;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Amount {
	public int total;
	public int tax_free;
	public int vat;
	public int point;
	public int discount;
}
