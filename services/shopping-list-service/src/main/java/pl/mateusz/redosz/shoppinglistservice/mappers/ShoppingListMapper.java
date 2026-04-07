package pl.mateusz.redosz.shoppinglistservice.mappers;

import pl.mateusz.redosz.shoppinglistservice.dtos.*;
import pl.mateusz.redosz.shoppinglistservice.entities.ShoppingItem;
import pl.mateusz.redosz.shoppinglistservice.entities.ShoppingList;

public class ShoppingListMapper {
    private ShoppingListMapper() {
    }

    public static ShoppingItem toEmbedded(ShoppingItemFormDto dto){
        return ShoppingItem.builder()
                .name(dto.name())
                .quantity(dto.quantity())
                .build();
    }

    public static ShoppingItemDto toDto(ShoppingItem item){
        return ShoppingItemDto.builder()
                .name(item.getName())
                .quantity(item.getQuantity())
                .checked(item.isChecked())
                .build();
    }

    public static ShoppingListDto toDto(ShoppingList list){
        return ShoppingListDto.builder()
                .id(list.getId())
                .createdAt(list.getCreatedAt())
                .title(list.getTitle())
                .build();
    }

    public static ShoppingList toEntity(ShoppingListFormDto dto){
        return ShoppingList.builder()
                .title(dto.title())
                .participantUsernames(dto.participantUsernames())
                .build();
    }

    public static ShoppingListDetailsDto toDetailsDto(ShoppingList list){
        return ShoppingListDetailsDto.builder()
                .id(list.getId())
                .createdAt(list.getCreatedAt())
                .title(list.getTitle())
                .items(list.getItems().stream().map(ShoppingListMapper::toDto).toList())
                .build();
    }
}
