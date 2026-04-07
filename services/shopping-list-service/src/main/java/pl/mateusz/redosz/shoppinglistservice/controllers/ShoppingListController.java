package pl.mateusz.redosz.shoppinglistservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mateusz.redosz.shoppinglistservice.dtos.*;
import pl.mateusz.redosz.shoppinglistservice.services.ShoppingListService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shoppings/lists")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @GetMapping
    public ShoppingListPageDto getUserAllShoppingList(
            @RequestHeader("X-User-Id") String username,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return shoppingListService.getUserAllShoppingList(username, page, size);
    }

    @GetMapping("/{listId}")
    public ShoppingListDetailsDto getUserShoppingListDetails(
            @RequestHeader("X-User-Id") String username,
            @PathVariable String listId
    ) {
        return shoppingListService.getUserShoppingListDetails(username, listId);
    }

    @PostMapping
    public void createList(@RequestBody ShoppingListFormDto formDto, @RequestHeader("X-User-Id") String username) {
        shoppingListService.createShoppingList(formDto, username);
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

    @PostMapping("/{listId}/participants")
    public void addParticipant(@PathVariable String listId, @RequestHeader("X-User-Id") String username) {
        shoppingListService.addNewParticipantToList(listId, username);
    }
}