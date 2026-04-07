package pl.mateusz.redosz.shoppinglistservice.dtos;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ShoppingListDetailsDto(String id,
                                     LocalDate createdAt,
                                     String title,
                                     List<ShoppingItemDto> items) {
}
