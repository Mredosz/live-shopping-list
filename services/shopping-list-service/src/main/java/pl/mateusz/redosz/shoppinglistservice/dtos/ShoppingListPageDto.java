package pl.mateusz.redosz.shoppinglistservice.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record ShoppingListPageDto(List<ShoppingListDto> items, boolean hasNext) {
}
