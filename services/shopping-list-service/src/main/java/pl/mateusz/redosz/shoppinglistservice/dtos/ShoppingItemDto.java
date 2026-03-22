package pl.mateusz.redosz.shoppinglistservice.dtos;

import lombok.Builder;

@Builder
public record ShoppingItemDto(String name,
                              int quantity,
                              boolean checked) {
}
