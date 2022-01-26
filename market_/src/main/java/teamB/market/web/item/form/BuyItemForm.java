package teamB.market.web.item.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BuyItemForm {
    private long id;
    private long itemId;
    private long memberId;
    private static final int count = 1;
    private int price;
}
