import { Component, inject, input, OnInit, signal } from '@angular/core';
import { ListsService } from '../../lists-service';
import { ShoppingListDetailsDto } from '../../lists-type';
import { ListItem } from '../list-item/list-item';
import { AddListItem } from '../add-list-item/add-list-item';

@Component({
  selector: 'app-list-details',
  imports: [ListItem, AddListItem],
  templateUrl: './list-details.html',
  styles: ``,
})
export class ListDetails implements OnInit {
  private readonly listsService = inject(ListsService);
  id = input.required<string>();
  list = signal<ShoppingListDetailsDto>({ createdAt: '', id: '', items: [], title: '' });

  isOpen = signal(false);

  ngOnInit(): void {
    this.listsService
      .fetchListDetails(this.id())
      .subscribe({ next: (list) => this.list.set(list) });
  }

  toggleIsOpen() {
    this.isOpen.update((prev) => !prev);
  }

  closeModal() {
    this.isOpen.set(false);
  }

  close() {
    this.closeModal();
    this.listsService
      .fetchListDetails(this.id())
      .subscribe({ next: (list) => this.list.set(list) });
  }
}
