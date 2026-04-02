import { Component, inject, OnInit, signal } from '@angular/core';
import { ListsService } from './lists-service';
import { ShoppingListPageDto } from './lists-type';
import { List } from './list/list';

@Component({
  selector: 'app-lists',
  imports: [List],
  templateUrl: './lists.html',
  standalone: true,
})
export class Lists implements OnInit {
  private readonly listsService = inject(ListsService);
  lists = signal<ShoppingListPageDto>({ items: [], hasNext: false });

  ngOnInit(): void {
    this.listsService.fetchAllLists().subscribe({ next: (lists) => this.lists.set(lists) });
  }
}
