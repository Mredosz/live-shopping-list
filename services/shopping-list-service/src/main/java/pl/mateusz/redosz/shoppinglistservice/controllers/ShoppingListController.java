package pl.mateusz.redosz.shoppinglistservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mateusz.redosz.shoppinglistservice.dtos.*;
import pl.mateusz.redosz.shoppinglistservice.services.ShoppingListService;

@RestController
@RequiredArgsConstructor
@RequestMapping("shoppings/lists")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @GetMapping("/{userId}")
    public ShoppingListPageDto getUserLists(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return shoppingListService.getUserAllShoppingList(userId, page, size);
    }

    @PostMapping
    public void createList(@RequestBody ShoppingListFormDto formDto) {
        shoppingListService.createShoppingList(formDto);
    }

    @PostMapping("/{listId}/items")
    public void addItem(@PathVariable String listId, @RequestBody ShoppingItemFormDto itemDto) {
        shoppingListService.addItemToList(listId, itemDto);
    }

    @PatchMapping("/{listId}/items/{itemName}")
    public void updateItemStatus(
            @PathVariable String listId,
            @PathVariable String itemName,
            @RequestBody UpdateShoppingItemStatusDto statusDto
    ) {
        shoppingListService.changeShoppingItemStatus(listId, itemName, statusDto);
    }

    @PostMapping("/{listId}/participants/{userId}")
    public void addParticipant(@PathVariable String listId, @PathVariable String userId) {
        shoppingListService.addNewParticipantToList(listId, userId);
    }
}