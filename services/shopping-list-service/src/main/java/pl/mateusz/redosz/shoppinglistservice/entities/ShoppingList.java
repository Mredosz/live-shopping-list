package pl.mateusz.redosz.shoppinglistservice.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document("shopping_lists")
public class ShoppingList {
    @Id
    private String id;

    @CreatedDate
    private LocalDate createdAt;
    private String title;
    @Builder.Default
    private List<ShoppingItem> items = new ArrayList<>();
    @Builder.Default
    private List<String> participantIds = new ArrayList<>();

    public void addItem(ShoppingItem item) {
        items.add(item);
    }

    public void addParticipant(String id) {
        participantIds.add(id);
    }
}