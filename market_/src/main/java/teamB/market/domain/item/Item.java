package teamB.market.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import teamB.market.domain.member.Route;
import teamB.market.domain.member.Address;
import teamB.market.domain.member.EmailAuth;
import teamB.market.domain.member.Role;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private long id;
    private long memberId;
    private Category category;
    private String name;
    private int price;
    private int hit;
    private String content;
    private String filePath;
    private String orderKey ; // item add 시 임의로 생성해서 넣어주는걸로
}
