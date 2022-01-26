package teamB.market.domain.shipping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipping {
	private long id;
	private long memberId;
	private long itemId;
	private Status shippingStatus;
}
