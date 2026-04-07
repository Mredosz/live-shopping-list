import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {
  ShoppingItemFormDto,
  ShoppingListDetailsDto,
  ShoppingListFormDto,
  ShoppingListPageDto,
  UpdateShoppingItemStatusDto,
} from './lists-type';

@Injectable({
  providedIn: 'root',
})
export class ListsService {
  private readonly httpClient = inject(HttpClient);

  fetchAllLists() {
    return this.httpClient.get<ShoppingListPageDto>('http://localhost:8080/shoppings/lists', {
      withCredentials: true,
    });
  }

  fetchListDetails(id: string) {
    return this.httpClient.get<ShoppingListDetailsDto>(
      `http://localhost:8080/shoppings/lists/${id}`,
      {
        withCredentials: true,
      },
    );
  }

  createList(formDto: ShoppingListFormDto) {
    return this.httpClient.post<void>('http://localhost:8080/shoppings/lists', formDto, {
      withCredentials: true,
    });
  }

  addItem(listId: string, itemDto: ShoppingItemFormDto) {
    return this.httpClient.post<void>(
      `http://localhost:8080/shoppings/lists/${listId}/items`,
      itemDto,
      {
        withCredentials: true,
      },
    );
  }

  updateItemStatus(listId: string, itemName: string, statusDto: UpdateShoppingItemStatusDto) {
    return this.httpClient.patch<void>(
      `http://localhost:8080/shoppings/lists/${listId}/items/${itemName}`,
      statusDto,
      {
        withCredentials: true,
      },
    );
  }

  addParticipant(listId: string, username: string) {
    return this.httpClient.patch<void>(
      `http://localhost:8080/shoppings/lists/${listId}/participants`,
      { username },
      {
        withCredentials: true,
      },
    );
  }
}
