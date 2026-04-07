import { Component, inject, OnInit, signal } from '@angular/core';
import { ListsService } from './lists-service';
import { ShoppingListPageDto } from './lists-type';
import { List } from './list/list';
import { ListCreateForm } from './list/list-create-form/list-create-form';
import { Modal } from '../../shared/modal/modal';

@Component({
  selector: 'app-lists',
  imports: [List, ListCreateForm, Modal],
  templateUrl: './lists.html',
  standalone: true,
})
export class Lists implements OnInit {
  private readonly listsService = inject(ListsService);
  lists = signal<ShoppingListPageDto>({ items: [], hasNext: false });

  isOpen = signal(false);

  ngOnInit(): void {
    this.listsService.fetchAllLists().subscribe({ next: (lists) => this.lists.set(lists) });
  }

  toggleModal() {
    this.isOpen.update((prev) => !prev);
  }

  closeModal() {
    this.isOpen.set(false);
  }

  close() {
    this.closeModal();
    this.listsService.fetchAllLists().subscribe({ next: (lists) => this.lists.set(lists) });
  }
}
