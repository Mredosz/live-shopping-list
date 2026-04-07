package pl.mateusz.redosz.shoppinglistservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.mateusz.redosz.shoppinglistservice.dtos.*;
import pl.mateusz.redosz.shoppinglistservice.mappers.ShoppingListMapper;
import pl.mateusz.redosz.shoppinglistservice.repositories.ShoppingListRepository;


@Service
@RequiredArgsConstructor
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListPageDto getUserAllShoppingList(String username, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        var slice = shoppingListRepository.findByParticipantUsernamesContains(username, pageable);
        var mapped = slice.getContent().stream().map(ShoppingListMapper::toDto).toList();

        return new ShoppingListPageDto(mapped, slice.hasNext());
    }

    public ShoppingListDetailsDto getUserShoppingListDetails(String username, String listId) {
        var list = shoppingListRepository.findByParticipantUsernamesContainsAndId(username, listId)
                .orElseThrow();
        return ShoppingListMapper.toDetailsDto(list);
    }

    public void addItemToList(String listId, ShoppingItemFormDto itemFormDto) {
        var list = shoppingListRepository.findById(listId).orElseThrow();
        var mappedItem = ShoppingListMapper.toEmbedded(itemFormDto);

        list.addItem(mappedItem);
        shoppingListRepository.save(list);
    }

    public void addNewParticipantToList(String listId, String username) {
        var list = shoppingListRepository.findById(listId).orElseThrow();

        list.addParticipant(username);
        shoppingListRepository.save(list);
    }

    public void createShoppingList(ShoppingListFormDto formDto, String username) {
        var mappedList = ShoppingListMapper.toEntity(formDto);
        mappedList.addParticipant(username);
        shoppingListRepository.save(mappedList);
    }

    public void changeShoppingItemStatus(String listId, String itemName, UpdateShoppingItemStatusDto statusDto) {
        var list = shoppingListRepository.findById(listId).orElseThrow();

        list.getItems().stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .ifPresent(i -> i.setChecked(statusDto.checked()));

        shoppingListRepository.save(list);
    }
}
