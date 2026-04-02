import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ShoppingListPageDto } from './lists-type';

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
}
