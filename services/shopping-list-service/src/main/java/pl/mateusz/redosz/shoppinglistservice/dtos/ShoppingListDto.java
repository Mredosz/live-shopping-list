package pl.mateusz.redosz.shoppinglistservice.dtos;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ShoppingListDto(String id,
                              LocalDate createdAt,
                              String title) {
}
