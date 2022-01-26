package teamB.market.web.item.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import teamB.market.domain.item.Category;

@Getter @Setter
public class EditItemForm {
    private long id;
    private long sellerId;
    private Category category;
    @NotBlank
    private String name;
    @NotNull
    private int price;
    @NotBlank
    private String content;
}
