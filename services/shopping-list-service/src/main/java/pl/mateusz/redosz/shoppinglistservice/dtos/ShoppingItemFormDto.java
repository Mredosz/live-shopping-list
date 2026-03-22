package pl.mateusz.redosz.shoppinglistservice.dtos;

import lombok.Builder;

@Builder
public record ShoppingItemFormDto(String name,
                                  int quantity) {
}
