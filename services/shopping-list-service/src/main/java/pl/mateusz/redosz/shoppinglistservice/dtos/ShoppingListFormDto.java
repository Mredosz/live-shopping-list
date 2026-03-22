package pl.mateusz.redosz.shoppinglistservice.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record ShoppingListFormDto(String title,
                                  List<String> participantIds) {
}
