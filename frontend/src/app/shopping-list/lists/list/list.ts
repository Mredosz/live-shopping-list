import { Component, input } from '@angular/core';
import { ShoppingListDto } from '../lists-type';

@Component({
  selector: 'app-list',
  imports: [],
  templateUrl: './list.html',
  standalone: true,
})
export class List {
  list = input.required<ShoppingListDto>();
}
