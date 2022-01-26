package teamB.market.web.socialAPI.kakao.approval;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalResponse {
	public String aid;
	public String tid;
	public String cid;
	public String sid;
	public String partner_order_id;
	public String partner_user_id;
	public String payment_method_type;
	public Amount amount;
	public CardInfo card_info;
	public String item_name;
	public String item_code;
	public Integer quantity;
	public String created_at;
	public String approved_at;
	public String payload;
}
