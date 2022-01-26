package teamB.market.domain.rate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Rate {
	private long id;
	private long itemId;
	private long memberId;
	private float rating;
	
}
