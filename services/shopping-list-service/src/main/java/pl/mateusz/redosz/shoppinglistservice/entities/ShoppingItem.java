package pl.mateusz.redosz.shoppinglistservice.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Builder
public class ShoppingItem {
    @Indexed(unique = true)
    private String name;
    private int quantity;

    @Builder.Default
    private boolean checked = false;
}
